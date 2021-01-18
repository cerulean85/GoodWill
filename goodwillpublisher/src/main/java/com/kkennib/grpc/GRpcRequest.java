package com.kkennib.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GRpcRequest {

//    public void enrollWork(String keyword, String siteType, String startDate, String endDate) {
//
//        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8081")
//                .usePlaintext(true)
//                .build();
//
//        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
//        GreetingServiceOuterClass.WorkRequest request =
//                GreetingServiceOuterClass.WorkRequest.newBuilder()
//                        .setKeyword(keyword)
//                        .setSiteType(siteType)
//                        .setStartDate(startDate)
//                        .setEndDate(endDate)
//                        .build();
//
//        GreetingServiceOuterClass.WorkResponse response = stub.greeting(request);
//        channel.shutdownNow();
//    }


}
