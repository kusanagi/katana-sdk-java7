package com.katana.sdk.common;

import org.zeromq.ZMQ;


/**
 * Created by juan on 1/10/16.
 */

public class ComponentWorker extends Thread {

    private final String workerEndpoint;

    private ZMQ.Context context;

    private ZMQ.Socket socketObj;

    private WorkerListener workerListener;

    public ComponentWorker(String workerEndpoint) {
        this.workerEndpoint = workerEndpoint;
    }

    public void setWorkerListener(WorkerListener workerListener) {
        this.workerListener = workerListener;
    }

    @Override
    public void run() {
        startSocket();
        int part = 0;
        String part1 = "";
        byte[] part2 = new byte[0];
        byte[] part3 = new byte[0];
        while (!Thread.currentThread().isInterrupted()) {
            part++;
            if (part == 1) {
                part1 = socketObj.recvStr();
            } else if (part == 2 && socketObj.hasReceiveMore()) {
                part2 = socketObj.recv();
            } else if (part == 3 && socketObj.hasReceiveMore()) {
                part3 = socketObj.recv();
            }
            if (!socketObj.hasReceiveMore()) {
                part = 0;
                socketObj.sendMore(new byte[]{});
                byte[] reply = workerListener.onRequestReceived(part1, part2.length == 0 ? null : part2, part3);
                socketObj.send(reply);
            }
        }
    }

    private void startSocket() {
        context = ZMQ.context(1);
        socketObj = context.socket(ZMQ.REP);
        socketObj.connect(this.workerEndpoint);
    }

    private void stopSocket() {
        socketObj.close();
        context.term();
    }

    public interface WorkerListener {
        byte[] onRequestReceived(String componentType, byte[] mappings, byte[] request);
    }
}
