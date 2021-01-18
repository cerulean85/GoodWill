package com.kkennib.producer;

import com.kkennib.keyword.KeywordFormat;
import lombok.SneakyThrows;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ProducerProcess implements Runnable {

    private List<KeywordFormat> keySet = new ArrayList<>();

    public ProducerProcess(List<KeywordFormat> keySet) {
        this.keySet = keySet;
    }

    @SneakyThrows
    @Override
    public void run() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String processPath = System.getProperty("user.dir") + "\\src\\main\\resources\\probe.exe ";
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(new PumpStreamHandler(outputStream));
        for(KeywordFormat kwdFormat : keySet) {
            String cmd = processPath + kwdFormat.getSiteType() + " " + kwdFormat.getKeyword() + " "
                        + kwdFormat.getStartDate() + " " +  kwdFormat.getEndDate() + " " + kwdFormat.getTopicName() + " " + kwdFormat.getGroupId();
            CommandLine cmdLine = CommandLine.parse(cmd);
            executor.execute(cmdLine);
        }

        String message = outputStream.toString("EUC-KR").trim();
        String[] probeResultArr =message.split("\n");

        processPath = System.getProperty("user.dir") + "\\src\\main\\resources\\collector.exe ";
        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        for(int i=0; i<probeResultArr.length; i++) {
            String probeResult = probeResultArr[i];
            if(probeResult.isEmpty()) continue;

            JSONObject jObject = new JSONObject(probeResult);
            System.out.println(jObject);

            JSONObject echoObj = jObject.getJSONObject("echo");
//            System.out.println(echoObj);
            String uSite = echoObj.getString("site");
            String uKeyword = echoObj.getString("keyword");
            String uStartDate = echoObj.getString("startDate");
            String uEndDate = echoObj.getString("endDate");
            String uTopicName = echoObj.getString("topicName");
            int utotalPageCount = jObject.getInt("totalPageCount");
            int isFinal = ( i == (probeResultArr.length - 1 ) ) ? 1 : 0;
//            String hashSource = uSite + uKeyword + uStartDate + uEndDate + (new Timestamp(System.currentTimeMillis()));
            String cmd = processPath + " " + isFinal + " " + uKeyword + " "
                    + uStartDate + " " +  uEndDate + " " + utotalPageCount + " " + uTopicName;
            System.out.println(cmd);

            CommandLine cmdLine = CommandLine.parse(cmd);
            executor = new DefaultExecutor();
            executor.execute(cmdLine, resultHandler);
        }
    }
}
