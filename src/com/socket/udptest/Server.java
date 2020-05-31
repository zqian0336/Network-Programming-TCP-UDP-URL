package com.socket.udptest;



import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


//在udp中并没有server的概念
//只有发送端和receive端的概念

//要等待客户端的连接！！！！
public class Server {

    public static void main(String[] args) throws Exception {

        //开放端口
        DatagramSocket socket = new DatagramSocket(9191);

        //接收数据包
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length); //接收

        socket.receive(packet); //阻塞接收
        System.out.println(packet.getAddress().getHostAddress());
        System.out.println(new String(packet.getData(), 0, packet.getLength()));

        socket.close();

    }
}
