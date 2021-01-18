package com.kkennib.consumer;

import com.kkennib.DBConn;
import com.kkennib.keyword.KeywordFormat;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import java.sql.SQLException;

public class ResumeConsumerExecutor extends ConsumerExecutor {

    public ResumeConsumerExecutor(String topicName) {
        super(topicName);
    }

    @Override
    void initOffset() throws ServletException, SQLException, ClassNotFoundException {

//        DBConn conn = new DBConn();
        KeywordFormat res = DBConn.getInstance().getKeyword(topicName);
//        conn.close();

        startOffset = res.getFinishedWorkCount();
        endOffset = res.getTotalWorkCount();
    }

    @SneakyThrows
    @Override
    public void run() {
        initOffset();
        doWork();
    }
}