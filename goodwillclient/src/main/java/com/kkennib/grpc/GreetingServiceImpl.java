package com.kkennib.grpc;

import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void createWork(GreetingServiceOuterClass.KeywordFormat request,
                           StreamObserver<GreetingServiceOuterClass.WorkResponse> responseObserver)
    {

    }

    @Override
    public void resumeWork(GreetingServiceOuterClass.KeywordFormat request,
                           StreamObserver<GreetingServiceOuterClass.WorkResponse> responseObserver)
    {

    }

    @Override
    public void beginWork(GreetingServiceOuterClass.KeywordFormat request,
                          StreamObserver<GreetingServiceOuterClass.WorkResponse> responseObserver) {

    }

    @Override
    public void pauseWork(GreetingServiceOuterClass.KeywordFormat request,
                          StreamObserver<GreetingServiceOuterClass.WorkResponse> responseObserver)
    {
    }

    @Override
    public void terminateWork(GreetingServiceOuterClass.KeywordFormat request,
                              StreamObserver<GreetingServiceOuterClass.WorkResponse> responseObserver)
    {
    }

    @Override
    public void getWorkTable(GreetingServiceOuterClass.KeywordFormat request,
                             StreamObserver<GreetingServiceOuterClass.WorkList> responseObserver)
    {

    }

}