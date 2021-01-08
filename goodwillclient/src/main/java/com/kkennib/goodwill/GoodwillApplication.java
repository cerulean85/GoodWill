package com.kkennib.goodwill;

import com.kkennib.grpc.GreetingServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GoodwillApplication {

    public static void main(String[] args) throws IOException, InterruptedException {




        SpringApplication.run(GoodwillApplication.class, args);



        // Create a new server to listen on port 8080
        Server server = ServerBuilder.forPort(8083)
                .addService(new GreetingServiceImpl())
                .build();

        // Start the server
        server.start();

        // Server threads are running in the background.
        System.out.println("Server started");
        // Don't exit the main thread. Wait until server is terminated.
        server.awaitTermination();

    }

}
