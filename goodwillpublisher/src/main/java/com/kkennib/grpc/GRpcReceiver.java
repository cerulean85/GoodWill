package com.kkennib.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GRpcReceiver {

    public void receive() throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(8085)
                .addService(new GreetingServiceImpl())
                .build();

        // Start the server
        server.start();

        // Server threads are running in the background.
        System.out.println("Publisher Receiving Packets...");
        // Don't exit the main thread. Wait until server is terminated.
        server.awaitTermination();
    }

}
