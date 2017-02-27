package com.katana.utils;

import org.zeromq.ZMQ;

/**
 * Created by juane on 2/11/17.
 */
public class TestClient extends Thread {

    private String addr;
    private byte[][] parts;

    private ZMQ.Context context;
    private ZMQ.Socket requester;

    private Listener listener;

    public TestClient(String addr, Listener listener, byte[]... parts) {
        this.addr = addr;
        this.parts = parts;
        this.listener = listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        open(addr);
        sendMessage(parts);
    }

    public void open(String tcp) {
        context = ZMQ.context(1);

        requester = context.socket(ZMQ.REQ);
        requester.connect(tcp);
    }

    public void sendMessage(byte[][] parts) {
        for (int i = 0; i < parts.length; i++) {
            requester.send(parts[i], i < parts.length - 1 ? zmq.ZMQ.ZMQ_SNDMORE : 0);
        }

        int part = 0;
        byte[] part1 = new byte[0];
        byte[] part2 = new byte[0];
        while (!Thread.currentThread().isInterrupted()) {
            part++;
            if (part == 1) {
                part1 = requester.recv();
            } else if (part == 2 && requester.hasReceiveMore()) {
                part2 = requester.recv();
            }
            if (!requester.hasReceiveMore()) {
                if (this.listener != null) {
                    this.listener.onReply(part1, part2);
                }
                Thread.currentThread().interrupt();
            }
        }
        close();
    }

    public void close() {
        requester.close();
    }

    public interface Listener {
        void onReply(byte[] part1, byte[] reply);
    }
}
