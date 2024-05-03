package org.players;

import java.util.concurrent.CountDownLatch;

public class SharedSingletonResource {

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    private SharedSingletonResource() {

    }

    private static SharedSingletonResource instance;

    public static SharedSingletonResource getInstance() {
        if (instance == null) {
            instance = new SharedSingletonResource();
        }
        return instance;
    }

    public CountDownLatch getSharedCountDownLatch() {
        return countDownLatch;
    }



}
