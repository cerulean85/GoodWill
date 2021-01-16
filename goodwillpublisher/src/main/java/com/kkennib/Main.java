package com.kkennib;

import com.kkennib.consumer.ConsumerDaemon;
import com.kkennib.grpc.GRpcReceiver;
import com.kkennib.grpc.GreetingServiceImpl;
import com.kkennib.keyword.KeywordFormat;
import com.kkennib.keyword.KeywordSetGenerator;
import com.kkennib.producer.ProducerProcess;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class Main {
    static Logger log = LoggerFactory.getLogger(ConsumerDaemon.class);

    public static void main( String[] args ) throws IOException, InterruptedException, ServletException, SQLException {

        DBConn conn = new DBConn();
        conn.updateState_Terminated("test");

//        Server server = ServerBuilder.forPort(8085)
//                .addService(new GreetingServiceImpl())
//                .build();
//
//        // Start the server
//        server.start();
//
//        // Server threads are running in the background.
//        System.out.println("Publisher Receiving Packets...");
//        // Don't exit the main thread. Wait until server is terminated.
//        server.awaitTermination();





//        String sites = "donga";
//        String operators = "and/or/and/or";
//        String keywords = "A/B/C/D";
//        String date = "20201001/20201031";
//
//        List<KeywordFormat> keySet =  (new KeywordSetGenerator(sites, operators, keywords, date)).generator();
//        ProducerProcess producerProcess = new ProducerProcess(keySet);
//        Thread pThd = new Thread(producerProcess);
//        pThd.start();
//
//        ConsumerDaemon consumerDaemon = new ConsumerDaemon(keySet);




//        ConsumerDaemon consumerDaemon = new ConsumerDaemon();
//        consumerDaemon.pushTopic("0c1a20aa5318ac014fccd8e7f1713bf1");
//        consumerDaemon.pushTopic("b0d6448fc4f78d0e9049b5df5c8066a2");
//        consumerDaemon.pushTopic("2e30e849b3513f0df5868bde3973731a");
//        consumerDaemon.pushTopic("ca16b2ea27ad9d5572df3c70592d0c61");
//        Thread cThd = new Thread(consumerDaemon);
//        cThd.start();
    }
}
