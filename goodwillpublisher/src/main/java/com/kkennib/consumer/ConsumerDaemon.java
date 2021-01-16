package com.kkennib.consumer;

import com.kkennib.keyword.KeywordFormat;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConsumerDaemon implements Runnable{
    Logger log = LoggerFactory.getLogger(ConsumerDaemon.class);

    public ConsumerDaemon(List<KeywordFormat> keySet) {
        setTopic(keySet);
    }

    public ConsumerDaemon() {

    }

    Queue<String> topics = new LinkedList<>();
    public void pushTopic(String topic) { topics.add(topic); }
    public void setTopic(List<KeywordFormat> keySet) {

        for(KeywordFormat e : keySet) {
            topics.add(e.getTopicName());
        }

    }

    List<ConsumerExecutor> exeList = new ArrayList<>();

    @SneakyThrows
    @Override
    public void run() {

        int timeCount = 0;
        while(true) {
//            log.info("Daemon Executing...");

            while(topics.size() > 0) {
                String topic = topics.poll();
                log.info(topic);
                ConsumerExecutor consumerExecutor = new ConsumerExecutor(topic);
                Thread t = new Thread(consumerExecutor);
                exeList.add(consumerExecutor);
                t.start();
            }

            Thread.sleep(3000);
            timeCount = timeCount + 3000;
            System.out.println(timeCount);
            if(timeCount > 30000) {
                for(ConsumerExecutor exe : exeList) {
                    exe.stop();

                }
                break;
            }
        }
    }
}
