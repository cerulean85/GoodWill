package com.kkennib.consumer;

import lombok.SneakyThrows;

public class BeginConsumerExecutor extends ConsumerExecutor {

    public BeginConsumerExecutor(String topicName) {
        super(topicName);
    }

    @Override
    void initOffset() {
        startOffset = 0;
        endOffset = getLastOffset() - 1;
    }

    @SneakyThrows
    @Override
    public void run() {
        initOffset();
        doWork();
    }
}