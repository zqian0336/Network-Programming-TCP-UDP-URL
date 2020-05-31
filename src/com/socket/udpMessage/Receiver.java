package com.socket.udpMessage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9992);

        while(true){

            //准备接收包裹
            byte[] buffer = new byte[1024];
            //因为可以接收来自多人的包裹，所以不用写发送端的port
            DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
            socket.receive(packet);

            //显示内容，直到断开说bye
            byte[] data = packet.getData();
            String receivedData = new String(data, 0, data.length);
            System.out.println(receivedData);
            if(receivedData.equals("bye")){
                break;
            }
        }
        socket.close();

    }
}
