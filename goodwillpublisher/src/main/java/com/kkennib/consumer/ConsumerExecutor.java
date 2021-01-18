package com.kkennib.consumer;

import com.kkennib.DBConn;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public abstract class ConsumerExecutor implements Runnable{
    Logger log = LoggerFactory.getLogger(ConsumerProcess.class);

    protected String topicName;
    public String getTopicName() { return topicName; }
    protected long startOffset;
    protected long endOffset;
    protected Properties configs;
    protected KafkaConsumer<String, String> consumer;
    protected TopicPartition partition0;
    protected int currentTimeoutSeconds = 0;
    protected List<ConsumerProcess> consumerProcessList = new ArrayList<>();

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

    protected long getLastOffset() {
        consumer.assign(Collections.singletonList(partition0));
        consumer.seekToEnd(Collections.singletonList(partition0));
        return consumer.position(partition0);
    }

    public void pause() throws ServletException, SQLException, ClassNotFoundException {
        for (ConsumerProcess p : consumerProcessList) {
            p.stop();
        }
        currentTimeoutSeconds = Config.TIME_OUT_MILLISECONDS;
        DBConn.getInstance().updateState_Paused(this.topicName);
        log.info("Thread-{} Stopped consumer executor!!", this.topicName);
    }

    public void terminate() throws ServletException, SQLException, ClassNotFoundException {
        for (ConsumerProcess p : consumerProcessList) {
            p.stop();
        }
        currentTimeoutSeconds = Config.TIME_OUT_MILLISECONDS;
        DBConn.getInstance().updateState_Terminated(this.topicName);
        log.info("Thread-{} Stopped consumer executor!!", this.topicName);
    }

    public void doWork() throws InterruptedException {
//        long lastOffset = getLastOffset() - 1;
//        startOffset = 0;
//        endOffset = lastOffset;
//        log.info("Taked Topic: {}", topicName);
//        log.info("LastOffset: {}", lastOffset);

        ConsumerProcess p = new ConsumerProcess(this.topicName);
        p.execute(startOffset, endOffset );
        consumerProcessList.add(p);

        currentTimeoutSeconds = 0;
        while(currentTimeoutSeconds < Config.TIME_OUT_MILLISECONDS) {

            Thread.sleep(Config.THREAD_SLEEP_MILLISECONDS);
            currentTimeoutSeconds += Config.THREAD_SLEEP_MILLISECONDS;
//            log.info("Topic-{}-TIME: {}", this.topicName, currentTimeoutSeconds);

            long lastOffset = getLastOffset() - 1;
//            log.info("Topic-{} URL Count: {}", this.topicName, lastOffset);
            if(lastOffset < 0) continue;
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

    abstract void initOffset() throws InterruptedException, ServletException, SQLException, ClassNotFoundException;
}
