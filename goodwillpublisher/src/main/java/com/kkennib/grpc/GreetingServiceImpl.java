package com.kkennib.grpc;

import com.kkennib.DBConn;
import com.kkennib.Util;
import com.kkennib.WorkState;
import com.kkennib.consumer.ConsumerDaemon;
import com.kkennib.keyword.KeywordFormat;
import com.kkennib.keyword.KeywordSetGenerator;
import com.kkennib.producer.ProducerProcess;
import io.grpc.stub.StreamObserver;
import org.checkerframework.checker.nullness.compatqual.KeyForDecl;

import javax.servlet.ServletException;
import java.security.Key;
import java.sql.SQLException;
import java.util.List;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void createWork(GreetingServiceOuterClass.KeywordFormat request,
                           StreamObserver<GreetingServiceOuterClass.WorkResponse> responseObserver)
    {
        try {
            String keywords = request.getKeywords();
            String siteTypes = request.getSiteTypes();
            String operators = request.getOperators();
            String dates = request.getDates();
            String groupId = Util.getMD5(keywords + siteTypes + operators + dates + Util.getTimestamp());

            int res = DBConn.getInstance().insertWorkGroup(groupId);

            GreetingServiceOuterClass.WorkResponse response = GreetingServiceOuterClass.WorkResponse.newBuilder()
                    .setGroupId(groupId)
                    .setResult(res > 0 ? "success" : "fail")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ServletException | SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void resumeWork(GreetingServiceOuterClass.KeywordFormat request,
                           StreamObserver<GreetingServiceOuterClass.WorkResponse> responseObserver)
    {
        try{
            String groupId = request.getGroupId();

            ConsumerDaemon consumerDaemon = ConsumerDaemon.getInstance();
            consumerDaemon.resume(groupId);

            GreetingServiceOuterClass.WorkResponse response = GreetingServiceOuterClass.WorkResponse.newBuilder()
                    .setGroupId(groupId)
                    .setResult("success")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ServletException | SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void beginWork(GreetingServiceOuterClass.KeywordFormat request,
                          StreamObserver<GreetingServiceOuterClass.WorkResponse> responseObserver) {

        String keywords = request.getKeywords();
        String siteTypes = request.getSiteTypes();
        String operators = request.getOperators();
        String dates = request.getDates();
        String groupId = request.getGroupId();

        List<KeywordFormat> keySet =  (new KeywordSetGenerator(siteTypes, operators, keywords, dates)).generator();
        ProducerProcess producerProcess = new ProducerProcess(keySet);
        Thread pThd = new Thread(producerProcess);
        pThd.start();

        ConsumerDaemon consumerDaemon = ConsumerDaemon.getInstance();
        consumerDaemon.setTopic(keySet);
        Thread cThd = new Thread(consumerDaemon);
        cThd.start();

        GreetingServiceOuterClass.WorkResponse response = GreetingServiceOuterClass.WorkResponse.newBuilder()
                .setGroupId(groupId)
                .setResult("success")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void pauseWork(GreetingServiceOuterClass.KeywordFormat request,
                           StreamObserver<GreetingServiceOuterClass.WorkResponse> responseObserver)
    {
        try {
            String groupId = request.getGroupId();
            ConsumerDaemon consumerDaemon = ConsumerDaemon.getInstance();
            consumerDaemon.pause(groupId);

            GreetingServiceOuterClass.WorkResponse response = GreetingServiceOuterClass.WorkResponse.newBuilder()
                    .setGroupId(groupId)
                    .setResult("success")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ServletException | SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void terminateWork(GreetingServiceOuterClass.KeywordFormat request,
                           StreamObserver<GreetingServiceOuterClass.WorkResponse> responseObserver)
    {
        try {
            String groupId = request.getGroupId();
            ConsumerDaemon consumerDaemon = ConsumerDaemon.getInstance();
            consumerDaemon.terminate(groupId);

            GreetingServiceOuterClass.WorkResponse response = GreetingServiceOuterClass.WorkResponse.newBuilder()
                    .setGroupId(groupId)
                    .setResult("success")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ServletException | SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void getCurrentState(GreetingServiceOuterClass.KeywordFormat request,
                         StreamObserver<GreetingServiceOuterClass.WorkResponse> responseObserver)
    {

        try {
            String groupId = request.getGroupId();
            List<KeywordFormat> keySet =   DBConn.getInstance().getKeywordSet(groupId);

            boolean isState = matchState(keySet,WorkState.TERMINATED);
            if(isState) {
                responseCurrentState(responseObserver, groupId, WorkState.TERMINATED, 0);
                return;
            }

            isState = matchState(keySet,WorkState.PAUSED);
            if(isState) {

                int totalCount = 0;
                int finisehdCount = 0;

                for (KeywordFormat kwdFormat : keySet) {
                    finisehdCount += kwdFormat.getFinishedWorkCount();
                    totalCount += kwdFormat.getTotalWorkCount();
                }

                float rate = (float) ( (totalCount == 0) ?  0.0 : (finisehdCount / totalCount));

                responseCurrentState(responseObserver, groupId, WorkState.TERMINATED, rate);
                return;
            }

            isState = matchState(keySet,WorkState.PROCESSING);
            if(isState) {

                int totalCount = 0;
                int finisehdCount = 0;

                for (KeywordFormat kwdFormat : keySet) {
                    finisehdCount += kwdFormat.getFinishedWorkCount();
                    totalCount += kwdFormat.getTotalWorkCount();
                }

                float rate = (float) ( (totalCount == 0) ?  0.0 : (finisehdCount / totalCount));

                responseCurrentState(responseObserver, groupId, WorkState.PROCESSING, rate);
                return;
            }

            isState = matchState(keySet,WorkState.WAITING);
            if(isState) {
                responseCurrentState(responseObserver, groupId, WorkState.WAITING, 0);
                return;
            }

            isState = matchState(keySet, WorkState.ENROLLED);
            if(isState) {
                responseCurrentState(responseObserver, groupId, WorkState.ENROLLED, 0);
                return;
            }

            isState = matchState(keySet,WorkState.FINISHED);
            if(isState) {
                responseCurrentState(responseObserver, groupId, WorkState.FINISHED, 0);
                return;
            }
        } catch (ServletException | SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void getWorkTable(GreetingServiceOuterClass.KeywordFormat request,
                             StreamObserver<GreetingServiceOuterClass.WorkList> responseObserver)
    {
        try {
            List<KeywordFormat> keySet = DBConn.getInstance().getWorkTable();

            GreetingServiceOuterClass.WorkList.Builder resBuilder = GreetingServiceOuterClass.WorkList.newBuilder();
            for (KeywordFormat kwd : keySet) {
                GreetingServiceOuterClass.KeywordFormat.Builder wb = GreetingServiceOuterClass.KeywordFormat.newBuilder();
                wb.setGroupId(Util.empty(kwd.getGroupId()));
                wb.setTopicName(Util.empty(kwd.getTopicName()));
                wb.setStartDate(Util.empty(kwd.getStartDate()));
                wb.setEndDate(Util.empty(kwd.getEndDate()));
                wb.setSiteType(Util.empty(kwd.getSiteType()));
                wb.setKeyword(Util.empty(kwd.getKeyword()));
                wb.setCurrentState(Util.empty(kwd.getCurrentState()));
                wb.setFinishedWorkCount(kwd.getFinishedWorkCount());
                wb.setTotalWorkCount(kwd.getTotalWorkCount());
                wb.setPackgedFileName(Util.empty(kwd.getPackagedFileName()));
                resBuilder.addList(wb.build());
            }

            GreetingServiceOuterClass.WorkList response = resBuilder.build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (ServletException | SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    private void responseCurrentState(StreamObserver<GreetingServiceOuterClass.WorkResponse> responseObserver, String groupId, String state, float rate) {
        GreetingServiceOuterClass.WorkResponse response = GreetingServiceOuterClass.WorkResponse.newBuilder()
                .setGroupId(groupId)
                .setResult(state)
                .setRate(rate)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    private boolean matchState(List<KeywordFormat> keySet, String state) {
        for (KeywordFormat kwdFormat : keySet) {
            if(kwdFormat.getCurrentState().equals(WorkState.WAITING)) {
                return true;
            }
        }
        return false;
    }
}