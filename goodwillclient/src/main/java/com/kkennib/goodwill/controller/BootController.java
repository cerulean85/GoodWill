package com.kkennib.goodwill.controller;

import com.kkennib.goodwill.KeywordFormat;
import com.kkennib.grpc.GreetingServiceGrpc;
import com.kkennib.grpc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BootController {

    @RequestMapping(value="/")
    public String index() {

//
//        // Channel is the abstraction to connect to a service endpoint
//        // Let's use plaintext communication because we don't have certs
//        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8085")
//                .usePlaintext(true)
//                .build();
//
//        // It is up to the client to determine whether to block the call
//        // Here we create a blocking stub, but an async stub,
//        // or an async stub with Future are always possible.
//        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
//        GreetingServiceOuterClass.HelloRequest request =
//                GreetingServiceOuterClass.HelloRequest.newBuilder()
//                        .setName("Here is!!!")
//                        .build();
//
//        // Finally, make the call using the stub
//        GreetingServiceOuterClass.HelloResponse response =
//                stub.greeting(request);
//
//        System.out.println(response);
//
//        // A Channel should be shutdown before stopping the process.
//        channel.shutdownNow();


        return "hello";
    }

    @RequestMapping(value="/gen_keywords")
    public String generateKeywords() {



        return "practice";
    }

    //http://localhost:7072/get_work_table
    @RequestMapping(value="/get_work_table")
    public String getWorkTable(Model model) {

        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8084")
                .usePlaintext(true)
                .build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingServiceOuterClass.KeywordFormat request = GreetingServiceOuterClass.KeywordFormat.newBuilder()
                .setTopicName("").build();
        GreetingServiceOuterClass.WorkList response = stub.getWorkTable(request);

        System.out.println(response);
        channel.shutdownNow();

        List<KeywordFormat> list = new ArrayList<>();
        for(GreetingServiceOuterClass.KeywordFormat r : response.getListList()) {
            KeywordFormat kwd = new KeywordFormat();
            kwd.setGroupId(r.getGroupId());
            kwd.setTopicName(r.getTopicName());
            kwd.setStartDate(r.getStartDate());
            kwd.setEndDate(r.getEndDate());
            kwd.setSiteType(r.getSiteType());
            kwd.setKeyword(r.getKeyword());
            kwd.setCurrentState(r.getCurrentState());
            kwd.setFinishedWorkCount(r.getFinishedWorkCount());
            kwd.setTotalWorkCount(r.getTotalWorkCount());
            kwd.setPackagedFileName(r.getPackgedFileName());
            list.add(kwd);
        }

//        model.addAttribute("group_id", group_id);
        model.addAttribute("list", list);

        return "practice";
    }

    @RequestMapping(value="/create_work")
    public String createWork() {

        String sites = "donga";
        String operators = "and/or/and/or";
        String keywords = "A/B/C/D";
        String dates = "20201001/20201031";

        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8084")
                .usePlaintext(true)
                .build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingServiceOuterClass.KeywordFormat request =
                GreetingServiceOuterClass.KeywordFormat.newBuilder()
                .setKeywords(keywords)
                .setOperators(operators)
                .setDates(dates)
                .setSiteTypes(sites)
                .build();

        GreetingServiceOuterClass.WorkResponse response = stub.createWork(request);
        System.out.println(response);
        channel.shutdownNow();

        return "practice";

    }

    @RequestMapping(value="/begin_work")
    public String beginWork() {

        String sites = "donga";
        String operators = "and/or/and/or";
        String keywords = "A/B/C/D";
        String dates = "20201001/20201031";

        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8085").build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingServiceOuterClass.KeywordFormat request =
                GreetingServiceOuterClass.KeywordFormat.newBuilder()
                        .setKeywords(keywords)
                        .setOperators(operators)
                        .setDates(dates)
                        .setSiteTypes(sites)
                        .build();

        GreetingServiceOuterClass.WorkResponse response = stub.beginWork(request);
        System.out.println(response);
        channel.shutdownNow();

        return "practice";
    }

    @RequestMapping(value="/resume_work")
    public String resumeWork(@RequestParam String group_id, Model model) {

//        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8085").build();
//        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
//        GreetingServiceOuterClass.KeywordFormat request =
//                GreetingServiceOuterClass.KeywordFormat.newBuilder()
//                        .setGroupId(group_id)
//                        .build();
//
//        GreetingServiceOuterClass.WorkResponse response = stub.beginWork(request);
//        System.out.println(response);
//        channel.shutdownNow();

        List<KeywordFormat> list = new ArrayList<>();
        KeywordFormat kkk = new KeywordFormat();
        kkk.setCurrentState("aaa");
        kkk.setKeyword("bbb");
        list.add(kkk);
        model.addAttribute("group_id", group_id);
        model.addAttribute("list", list);

        return "practice";
    }
}
