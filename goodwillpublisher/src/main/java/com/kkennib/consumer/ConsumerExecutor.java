package com.kkennib.consumer;

import com.kkennib.DBConn;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.*;

public class ConsumerExecutor implements Runnable{

    Logger log = LoggerFactory.getLogger(ConsumerProcess.class);

    private String topicName;
    private Properties configs;
    private KafkaConsumer<String, String> consumer;
    private TopicPartition partition0;
    private int currentTimeoutSeconds = 0;

    public ConsumerExecutor(String topicName) {
        this.topicName = topicName;
        this.configs = new Properties();

        configs.put("bootstrap.servers", "localhost:9092");
        configs.put("session.timeout.ms", "10000");
        configs.put("group.id", topicName);
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configs.put("enable.auto.commit", "false");

        this.consumer = new KafkaConsumer<String, String>(configs);
        this.partition0 = new TopicPartition(topicName, 0);
    }

    private long getLastOffset() {

        consumer.assign(Collections.singletonList(partition0));
        consumer.seekToEnd(Collections.singletonList(partition0));
        return consumer.position(partition0);
    }

    List<ConsumerProcess> consumerProcessList = new ArrayList<>();
    public void stop() throws ServletException, SQLException {
        for (ConsumerProcess p : consumerProcessList) {
            p.stop();
        }
        currentTimeoutSeconds = Config.TIME_OUT_MILLISECONDS;

        DBConn conn = new DBConn();
        conn.updateState_Paused(this.topicName);
        conn.close();

        log.info("Thread-{} Stopped consumer executor!!", this.topicName);
    }

    @SneakyThrows
    @Override
    public void run() {
        long lastOffset = getLastOffset() - 1;
        long startOffset = 0;
        long endOffset = lastOffset;
        log.info("Taked Topic: {}", topicName);
        log.info("LastOffset: {}", lastOffset);

        ConsumerProcess p = new ConsumerProcess(this.topicName);
        p.execute(startOffset, endOffset );
        consumerProcessList.add(p);

        currentTimeoutSeconds = 0;
        while(currentTimeoutSeconds < Config.TIME_OUT_MILLISECONDS) {

            Thread.sleep(Config.THREAD_SLEEP_MILLISECONDS);
            currentTimeoutSeconds += Config.THREAD_SLEEP_MILLISECONDS;
//            log.info("Topic-{}-TIME: {}", this.topicName, currentTimeoutSeconds);

            lastOffset = getLastOffset() - 1;
//            log.info("Topic-{} URL Count: {}", this.topicName, lastOffset);
            if(lastOffset < 0)
                continue;

            if(endOffset < lastOffset) {
                log.info("Topic-{} is updated!", this.topicName);

                startOffset = endOffset + 1;
                endOffset = lastOffset;

                p = new ConsumerProcess(this.topicName);
                p.execute(startOffset, endOffset );
                consumerProcessList.add(p);

                currentTimeoutSeconds = 0;
            }
        }
        log.info("Topic-{} is closed by time over...", this.topicName);

    }
}