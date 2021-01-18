package com.kkennib.consumer;

import com.kkennib.DBConn;
import com.kkennib.WorkState;
import com.kkennib.keyword.KeywordFormat;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConsumerDaemon implements Runnable{
    Logger log = LoggerFactory.getLogger(ConsumerDaemon.class);

    private List<ConsumerExecutor> exeList = new ArrayList<>();

    private static ConsumerDaemon daemon = null;
    public static ConsumerDaemon getInstance() {
        if(daemon == null) {
            daemon = new ConsumerDaemon();
        }
        return daemon;
    }

    public ConsumerDaemon() { }
    public ConsumerDaemon(List<KeywordFormat> keySet) {
        setTopic(keySet);
    }

    Queue<String> topics = new LinkedList<>();
    public void pushTopic(String topic) { topics.add(topic); }
    public void setTopic(List<KeywordFormat> keySet) {
        for(KeywordFormat e : keySet) {
            topics.add(e.getTopicName());
        }
    }

    public void resume(String groupId) throws ServletException, SQLException, ClassNotFoundException {

        List<KeywordFormat> res = DBConn.getInstance().getOffset(groupId);
        for (KeywordFormat kwd : res) {
            topics.add(kwd.getTopicName());
        }

    }

    public void pause(String groupId) throws ServletException, SQLException, ClassNotFoundException {
        List<KeywordFormat> keySet = DBConn.getInstance().getKeywordSet(groupId);
        for(ConsumerExecutor exe : exeList) {
            for(KeywordFormat kwd: keySet){
                if(kwd.getTopicName().equals(exe.getTopicName())) {
                    exe.pause();
                }
            }
        }
    }

    public void terminate(String groupId) throws ServletException, SQLException, ClassNotFoundException {
        List<KeywordFormat> keySet = DBConn.getInstance().getKeywordSet(groupId);
        for(ConsumerExecutor exe : exeList) {
            for(KeywordFormat kwd: keySet){
                if(kwd.getTopicName().equals(exe.getTopicName())) {
                    exe.terminate();
                }
            }
        }
    }

    @SneakyThrows
    @Override
    public void run() {

        while(true) {
//            log.info("Daemon Executing...");

            while(topics.size() > 0) {
                String topic = topics.poll();
                log.info(topic);

                ConsumerExecutor exe = null;
                KeywordFormat res = DBConn.getInstance().getKeyword(topic);
                if(res.getCurrentState().equals(WorkState.WAITING)) {
                    exe = new BeginConsumerExecutor(topic);
                }
                else if(res.getCurrentState().equals(WorkState.PAUSED)) {
                    exe = new ResumeConsumerExecutor(topic);
                }

                if(exe != null) {
                    Thread t = new Thread(exe);
                    exeList.add(exe);
                    t.start();
                }
            }

            Thread.sleep(3000);

        }
    }
}
