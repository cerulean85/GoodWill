package com.kkennib.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MessagingSendTest {

    public void test() {
        Properties configs = new Properties();
        configs.put("bootstrap.servers", "localhost:9092");
        configs.put("acks", "all");
        configs.put("block.on.buffer.full", "true");
        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);

        for (int i = 0; i < 5; i++) {
            String v = "yyyyhello"+i;
            producer.send(new ProducerRecord<String, String>("test001", v));
        }

        producer.flush();
        producer.close();

    }

}
