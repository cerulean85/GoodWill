package com.kkennib;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

public class Worker implements Runnable{
    Logger log = LoggerFactory.getLogger(Worker.class);

    private int unitItemCount = 10;
    private String topicName;
    private int itemCount;
    private DefaultExecuteResultHandler resultHandler = null;


    public Worker(String topicName ) {
        this.topicName = topicName;
    }

    private void process(int processNo, long startOffset, long endOffset) throws IOException {
        log.info("processNo: {}, startOffset: {}, endOffset: {}", processNo, startOffset, endOffset);
        String processPath = System.getProperty("user.dir") + "\\src\\main\\resources\\scrapper.exe ";
        String cmd = processPath + processNo + " " + topicName + " " + startOffset + " " + endOffset;
        CommandLine cmdLine = CommandLine.parse(cmd);
        DefaultExecutor executor = new DefaultExecutor();
        if(resultHandler == null) {
            resultHandler = new DefaultExecuteResultHandler();
        }
        executor.execute(cmdLine, resultHandler);
    }

    private long getLastOffset() {
        Properties configs = new Properties();
        configs.put("bootstrap.servers", "localhost:9092");
        configs.put("session.timeout.ms", "10000");
        configs.put("group.id", topicName);
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configs.put("enable.auto.commit", "false");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configs);
        TopicPartition partition0 = new TopicPartition(topicName, 0);
        consumer.assign(Arrays.asList(partition0));
        consumer.seekToEnd(Arrays.asList(partition0));

        return consumer.position(partition0);
    }

    @Override
    public void run() {

        long updatedOffset = getLastOffset();

        int processCount = (int) (updatedOffset / unitItemCount);
        long startOffset = 0;
        long endOffset = 0;
        for(int i=0; i<processCount; i++) {
            startOffset = unitItemCount * i;
            endOffset = unitItemCount * (i+1) - 1;
            try {
                process(i, startOffset, endOffset);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        int remainCount = (int) (updatedOffset % unitItemCount);
        if(remainCount > 0) {
            startOffset = endOffset + 1;
            endOffset = updatedOffset;
            try {
                process(processCount, startOffset, endOffset);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
