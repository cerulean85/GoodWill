package com.kkennib;

import com.kkennib.grpc.GreetingServiceImpl;
import com.kkennib.grpc.PacketReceiver;
import com.kkennib.grpc.PacketResponser;
import com.kkennib.kafka.MessageReceiveTest;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main( String[] args ) throws Exception
    {

//        38fd6dd8679c23c9c734b1018ea2b396
//        adf2fbe5afcc85a0195fbf3c0aea2e76
//        c4ef1ff172ffcd59a75bc598934ba22

        Worker worker = new Worker("38fd6dd8679c23c9c734b1018ea2b396");
        Thread t = new Thread(worker, "Fi");
        t.start();

/*
        try {
            PacketResponser responser = new PacketResponser();
            responser.responseTest();
        } catch(Exception e) {
            System.out.println("Client doesn't work. Please check it.");
            e.printStackTrace();
        }
*/
/*

        PacketReceiver receiver = new PacketReceiver();
        receiver.receive();

        try {
            MessageReceiveTest mrt = new MessageReceiveTest();
            mrt.test();
        } catch(Exception e) {
            System.out.println("Subscriber Server can't start. Please check it.");
            e.printStackTrace();
        }
*/
    }
}
