package com.kkennib.consumer;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//public class ConsumerProcess implements Runnable{
public class ConsumerProcess {
//    private final long lastOffset;
    Logger log = LoggerFactory.getLogger(ConsumerProcess.class);

    private String topicName;
    public String getTopicName() { return topicName; }
    private DefaultExecuteResultHandler resultHandler = null;

    public ConsumerProcess(String topicName) {
        this.topicName = topicName;
//        this.lastOffset = lastOffset;
    }

    List<Process> processList = new ArrayList<>();

    private void process(long processNo, long startOffset, long endOffset) throws IOException {
//        log.info("processNo: {}, startOffset: {}, endOffset: {}", processNo, startOffset, endOffset);
        String processPath = System.getProperty("user.dir") + "\\src\\main\\resources\\scrapper.exe ";
        String cmd = processPath + processNo + " " + topicName + " " + startOffset + " " + endOffset;
        Process p = Runtime.getRuntime().exec(cmd);
        processList.add(p);
//        System.out.println("zzzzzz1212");
//        CommandLine cmdLine = CommandLine.parse(cmd);
//        DefaultExecutor executor = new DefaultExecutor();
//        if(resultHandler == null) {
//            resultHandler = new DefaultExecuteResultHandler();
//        }
//        executor.execute(cmdLine, resultHandler);
    }
    public void stop() {
        for (Process p : processList) {
            p.destroy();
            log.info("{} Process Killed!!", p.pid());
        }
    }
//    @Override
    public void execute(long startOffset, long endOffset) {

        long totalOffsetCount = endOffset - startOffset + 1;
        long alignedOffsetCount = 10;
        long processCount = (int) (totalOffsetCount / alignedOffsetCount);

        for(long i = 0; i < processCount; i++) {
            long _startOffset = alignedOffsetCount * i;
            long _endOffset = _startOffset + ( alignedOffsetCount - 1 );
//            log.info("startOffset: {}, endOffset: {}", _startOffset, _endOffset);
            try {
                process(i, _startOffset, _endOffset);
                Thread.sleep(500);
            } catch(IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        long remainCount = totalOffsetCount % alignedOffsetCount;
        if(remainCount > 0) {
            long _startOffset = startOffset + ( alignedOffsetCount * processCount );
            long _endOffset = _startOffset + remainCount - 1;
//            log.info("startOffset: {}, endOffset: {}", _startOffset, _endOffset);
            try {
                process(processCount, _startOffset, _endOffset);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
