package com.kkennib;

import com.kkennib.grpc.GreetingServiceImpl;
import com.kkennib.grpc.PacketReceiver;
import com.kkennib.grpc.PacketResponser;
import com.kkennib.kafka.MessagingSendTest;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class Main {
    public static void main( String[] args )
    {
        try {
            PacketResponser responser = new PacketResponser();
            responser.responseTest();
        } catch(Exception e) {
            e.printStackTrace();
        }

        MessagingSendTest msgTest = new MessagingSendTest();
        msgTest.test();

        try {
            PacketReceiver receiver = new PacketReceiver();
            receiver.receive();
        } catch(Exception e) {
            System.out.println("Subscriber Server can't start. Please check it.");
            e.printStackTrace();
        }

    }
}
