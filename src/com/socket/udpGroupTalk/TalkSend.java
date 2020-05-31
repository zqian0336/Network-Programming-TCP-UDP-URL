package com.socket.udpGroupTalk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TalkSend implements Runnable{
    DatagramSocket socket = null;
    BufferedReader reader = null;

    private int fromPort;
    private int toPort;
    private String toIP;
    private String msgFrom;

    public TalkSend(String msgFrom, int fromPort, int toPort, String toIP) {
        this.msgFrom = msgFrom;
        this.fromPort = fromPort;
        this.toPort = toPort;
        this.toIP = toIP;
        try {
            socket = new DatagramSocket(fromPort);
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //准备数据buffer
        while (true){
            String data = null;
            try {
                data = reader.readLine();
                byte[] buffer = data.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length, new InetSocketAddress(this.toIP,this.toPort));
                socket.send(packet);
                String sentData = new String(buffer, 0, buffer.length);
                System.out.println(msgFrom+" : "+sentData);


                if (data.equals("bye")){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        socket.close();
    }
}
