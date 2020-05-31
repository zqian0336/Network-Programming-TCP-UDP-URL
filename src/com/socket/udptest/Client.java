package com.socket.udptest;


import java.net.*;

//udp就是发短信
public class Client {
    public static void main(String[] args) throws Exception {
        //1 - 建立socket
        DatagramSocket socket = new DatagramSocket();
        //2 - 建立包
        String msg = "Hello server";

        InetAddress localhost = InetAddress.getByName("localhost");
        int port = 9191;

        //数据，数据的长度起始，要发送给谁
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), 0, msg.getBytes().length, localhost, port);

        //3 - 发送包
        socket.send(packet);

        socket.close();

    }
}
