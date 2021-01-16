package com.kkennib.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.time.Duration;

public class MessageReceiveTest {

    public void test() {

        System.out.print("Messaging Receving...");
        Properties configs = new Properties();

        configs.put("bootstrap.servers", "localhost:9092");
        configs.put("session.timeout.ms", "10000");
        configs.put("group.id", "test001");
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configs);
        consumer.subscribe(Arrays.asList("test001"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(500);
            for (ConsumerRecord<String, String> record : records) {
                String s = record.topic();
                if ("test001".equals(s)) {
                    System.out.println(record.value());
                } else {
                    throw new IllegalStateException("get message on topic " + record.topic());
                }
            }
        }
    }


    public String getText() {

        System.out.print("Messaging Receving...");
        Properties configs = new Properties();

        configs.put("bootstrap.servers", "localhost:9092");
        configs.put("session.timeout.ms", "10000");
        configs.put("group.id", "test001");
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configs);
        consumer.subscribe(Arrays.asList("test001"));
        String txt = "";
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(500);
            for (ConsumerRecord<String, String> record : records) {
                String s = record.topic();
                if ("test001".equals(s)) {
                    System.out.println(record.value());
                    txt += record.value();
                    continue;
                } else {
                    throw new IllegalStateException("get message on topic " + record.topic());
                }
            }
            return txt;
        }
    }

}

