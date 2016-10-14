package com.katana.sdk;

import com.katana.api.commands.ActionCommandPayload;
import com.katana.api.common.*;
import com.katana.sdk.common.Logger;
import com.katana.sdk.common.MessagePackSerializer;
import com.katana.sdk.common.Serializer;
import org.zeromq.ZMQ;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Created by juan on 1/10/16.
 */
public class TestClient {

    private final CountDownLatch countDownLatch;

    String componentName;
    String componentVersion;
    String action;
    String endpoint;
    int numThreads;
    boolean debug;

    public TestClient(String componentName, String componentVersion, String action, String endpoint, int numThreads, boolean debug, CountDownLatch countDownLatch) {
        this.componentName = componentName;
        this.componentVersion = componentVersion;
        this.action = action;
        this.endpoint = endpoint;
        this.numThreads = numThreads;
        this.debug = debug;
        this.countDownLatch = countDownLatch;
    }

    public void start() throws InterruptedException {
        Logger.log("Dummy started!");
        final ZMQ.Context context = ZMQ.context(1);

        Thread thread = new Thread() {
            public void run() {
                Logger.log("Thread run!");
                ZMQ.Socket requester = context.socket(ZMQ.REQ);
                requester.connect("ipc://" + endpoint);

                ActionCommandPayload actionCommandPayload = new ActionCommandPayload();
                ActionCommandPayload.ActionCommand actionCommand = new ActionCommandPayload.ActionCommand();
                Action action = new Action("list", componentName, componentVersion, "12", new HashMap<>(), false);
                Transport transport = new Transport();
                transport.setMeta(new TransportMeta());
                action.setTransport(transport);
                actionCommand.setArgument(action);
                actionCommandPayload.setCommand(actionCommand);

                Serializer serializer = new MessagePackSerializer();

                requester.send(serializer.write(actionCommandPayload), 0);
//                Logger.log("Client sent: " + serializer.writeToString(actionCommandPayload));
//                byte[] recv = requester.recv();
                String recvString = requester.recvStr();
                Logger.log("Client received something: " + recvString);
//                CommandReplyPayload read = serializer.read(recv, CommandReplyPayload.class);
//                Logger.log("Client received something: " + serializer.writeToString(read));

                requester.close();
                countDownLatch.countDown();
            }
        };
        thread.start();

        context.term();

    }

}
