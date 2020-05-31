package com.socket.udpMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;


public class Sender {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9998);

        //准备数据，控制台读取system.in
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String data = reader.readLine();
            byte[] buffer = data.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length, new InetSocketAddress("localhost",9992));
            socket.send(packet);
            if(data.equals("bye")){
                break;
            }
        }


        socket.close();



    }
}
