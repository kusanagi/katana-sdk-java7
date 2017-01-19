package com.katana.sdk.components;

import com.katana.sdk.common.Logger;
import org.zeromq.ZMQ;


/**
 * Created by juan on 1/10/16.
 */

public class ComponentWorker extends Thread {

    private final String workerEndpoint;

    private ZMQ.Context context;

    private ZMQ.Socket socketObj;

    private WorkerListener workerListener;

    public ComponentWorker(String workerEnpoint) {
        this.workerEndpoint = workerEnpoint;
    }

    public void setWorkerListener(WorkerListener workerListener) {
        this.workerListener = workerListener;
    }

    @Override
    public void run() {
        Logger.log("Component worker run");
        startSocket();
        Logger.log("Component worker socket started!");
        int part = 0;
        String part1 = "";
        byte[] part2 = new byte[0];
        byte[] part3 = new byte[0];
        while (!Thread.currentThread().isInterrupted()) {
            Logger.log("Component worker loop started! ");
            part++;
            if (part == 1) {
                part1 = socketObj.recvStr();
                Logger.log("Part 1 received: " + part1);
            } else if (part == 2 && socketObj.hasReceiveMore()) {
                part2 = socketObj.recv();
                Logger.log("Part 2 received: " + new String(part2));
            } else if (part == 3 && socketObj.hasReceiveMore()) {
                part3 = socketObj.recv();
                Logger.log("Part 3 received: " + new String(part3));
            }
            if (!socketObj.hasReceiveMore()) {
                part = 0;
                socketObj.sendMore(new byte[]{});
                byte[] reply = workerListener.onRequestReceived(part1, part2, part3);
                socketObj.send(reply);
                Logger.log("Component worker bytes sent!");
            }
        }
    }

    private void startSocket() {
        context = ZMQ.context(1);
        Logger.log("Component worker context created");
        socketObj = context.socket(ZMQ.REP);
        Logger.log("Component worker socket created");
        socketObj.connect(this.workerEndpoint);
        Logger.log("Component worker connected to " + this.workerEndpoint);
    }

    private void stopSocket() {
        socketObj.close();
        context.term();
    }

    public interface WorkerListener {
        byte[] onRequestReceived(String componentType, byte[] mappings, byte[] request);
    }
}
