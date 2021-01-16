package com.kkennib.grpc;

import com.kkennib.consumer.ConsumerDaemon;
import com.kkennib.keyword.KeywordFormat;
import com.kkennib.keyword.KeywordSetGenerator;
import com.kkennib.producer.ProducerProcess;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        // HelloRequest has toString auto-generated.
        System.out.println(request);

        String sites = "donga";
        String operators = "and/or/and/or";
        String keywords = "A/B/C/D";
        String date = "20201001/20201031";

        // DB ready state
        // DB에 키워드 정보 입력, 링크 정보 획득
        List<KeywordFormat> keySet =  (new KeywordSetGenerator(sites, operators, keywords, date)).generator();
        ProducerProcess producerProcess = new ProducerProcess(keySet);
        Thread pThd = new Thread(producerProcess);
        pThd.start();

        // DB enrolled state
        // DB에 키워드 정보 입력


        // DB waiting state
        //



        ConsumerDaemon consumerDaemon = new ConsumerDaemon(keySet);
        Thread cThd = new Thread(consumerDaemon);
        cThd.start();




        // You must use a builder to construct a new Protobuffer object
        GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.HelloResponse.newBuilder()
                .setGreeting("Hello there, " + request.getName())
                .build();

        // Use responseObserver to send a single response back
        responseObserver.onNext(response);

        // When you are done, you must call onCompleted.
        responseObserver.onCompleted();
    }
}