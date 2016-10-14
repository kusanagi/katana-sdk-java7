package com.katana.sdk.components;

import com.katana.api.commands.common.CommandArgument;
import com.katana.sdk.common.Callable;
import com.katana.sdk.common.Constants;
import com.katana.sdk.common.Logger;
import org.zeromq.ZMQ;

import java.util.Arrays;

/**
 * Created by juan on 1/10/16.
 */
public class ComponentWorker<T extends CommandArgument> extends Thread {

    private final Callable<T> callable;

    private ZMQ.Context context;

    private ZMQ.Socket socketObj;

    private WorkerListener<T> workerListener;

    public ComponentWorker(Callable<T> callable) {
        this.callable = callable;
    }

    public void setWorkerListener(WorkerListener<T> workerListener) {
        this.workerListener = workerListener;
    }

    @Override
    public void run() {
        Logger.log("Component worker run");
        try {
            startSocket();
            Logger.log("Component worker socket started!");
            while (!Thread.currentThread().isInterrupted()) {
                Logger.log("Component worker loop started! ");
                byte[] request = socketObj.recv();
                Logger.log("Component worker bytes received!: " + Arrays.toString(request));
                byte[] reply = workerListener.onRequestReceived(request, this.callable);
                socketObj.send(reply);
                Logger.log("Component worker bytes sent!: " + Arrays.toString(reply));
            }
        } catch (Exception e) {
            Logger.log(e);
        }finally {
            stopSocket();
            Logger.log("Component worker socket stopped!");
        }
    }

    private void startSocket() {
        context = ZMQ.context(1);
        Logger.log("Component worker context created");
        socketObj = context.socket(ZMQ.REP);
        Logger.log("Component worker socket created");
        socketObj.connect(Constants.WORKER_ENDPOINT);
        Logger.log("Component worker connected to " + Constants.WORKER_ENDPOINT);
    }

    private void stopSocket() {
        socketObj.close();
        context.term();
    }

    public interface WorkerListener<T extends CommandArgument> {
        byte[] onRequestReceived(byte[] request, Callable<T> callable);
    }
}
