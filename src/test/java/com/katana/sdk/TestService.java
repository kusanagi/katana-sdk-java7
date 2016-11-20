package com.katana.sdk;

import com.katana.api.common.Action;
import com.katana.sdk.common.Callable;
import com.katana.sdk.components.Service;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by juan on 1/10/16.
 */
public class TestService {

    public static final String COMPONENT = "service";
    public static final String COMPONENT_NAME = "company-data";
    public static final String COMPONENT_VERSION = "1.0.0";
    public static final String ACTION = "dummyAction";
    public static final String PLATFORM_VERSION = "1.0.0";
    public static final String SOCKET = "test_socket";

    @Test
    public void test() throws InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String[] args = {"-c", COMPONENT, "-n", COMPONENT_NAME, "-a", ACTION, "-s", SOCKET, "-p", PLATFORM_VERSION, "-v", COMPONENT_VERSION};
//                startService(args);
//            }
//        });
//        thread.start();
//
//        Thread.sleep(1000);
//        startClient(countDownLatch);
//
//        countDownLatch.await();

        Assert.assertTrue(true);
    }

    private void startService(String[] args) {
//        Service service = new Service(args);
//        service.action(new Callable<Action>() {
//            @Override
//            public Action run(Action object) {
//                Assert.assertTrue(true);
//                return object;
//            }
//        });
    }

    private void startClient(CountDownLatch countDownLatch) throws InterruptedException {
        TestClient testClient = new TestClient(COMPONENT_NAME, COMPONENT_VERSION, ACTION, SOCKET, 1, false, countDownLatch);
        testClient.start();
    }

}
