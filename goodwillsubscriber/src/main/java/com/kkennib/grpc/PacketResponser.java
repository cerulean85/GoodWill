package com.kkennib.grpc;

import com.kkennib.kafka.MessageReceiveTest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class PacketResponser {

    public void responseTest() {
        // Channel is the abstraction to connect to a service endpoint
        // Let's use plaintext communication because we don't have certs
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8083")
                .usePlaintext(true)
                .build();

        String txt = "Here is Subscriber Server !!";
        try {
            MessageReceiveTest mrt = new MessageReceiveTest();
            txt = mrt.getText();
        } catch(Exception e) {
            System.out.println("Subscriber Server can't start. Please check it.");
            e.printStackTrace();
        }

        // It is up to the client to determine whether to block the call
        // Here we create a blocking stub, but an async stub,
        // or an async stub with Future are always possible.
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingServiceOuterClass.HelloRequest request =
                GreetingServiceOuterClass.HelloRequest.newBuilder()
                        .setName(txt)
                        .build();

        // Finally, make the call using the stub
        GreetingServiceOuterClass.HelloResponse response =
                stub.greeting(request);

        System.out.println(response);

        // A Channel should be shutdown before stopping the process.
        channel.shutdownNow();
    }

}
