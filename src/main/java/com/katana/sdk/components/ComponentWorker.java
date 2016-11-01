package com.katana.sdk.components;

import com.katana.api.common.Api;
import com.katana.sdk.common.Callable;
import com.katana.sdk.common.Logger;
import org.zeromq.ZMQ;


/**
 * Created by juan on 1/10/16.
 */

/**
 *
 * @param <T>
 */
public class ComponentWorker<T extends Api> extends Thread {

    private final Callable<T> callable;

    private final String workerEndpoint;

    private ZMQ.Context context;

    private ZMQ.Socket socketObj;

    private WorkerListener<T> workerListener;

    public ComponentWorker(Callable<T> callable, String workerEnpoint) {
        this.callable = callable;
        this.workerEndpoint = workerEnpoint;
    }

    public void setWorkerListener(WorkerListener<T> workerListener) {
        this.workerListener = workerListener;
    }

    @Override
    public void run() {
        Logger.log("Component worker run");
        startSocket();
        Logger.log("Component worker socket started!");
        while (!Thread.currentThread().isInterrupted()) {
            Logger.log("Component worker loop started! ");
//            String requestString = socketObj.recvStr();
//            Logger.log(requestString);
//            byte[] request = new byte[0];
            byte[] request = socketObj.recv();
            Logger.log("Component worker bytes received!");
            socketObj.sendMore(new byte[]{});
            byte[] reply = workerListener.onRequestReceived(request, this.callable);
            socketObj.send(reply);
            Logger.log("Component worker bytes sent!");
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

    public interface WorkerListener<T extends Api> {
        byte[] onRequestReceived(byte[] request, Callable<T> callable);
    }
}
