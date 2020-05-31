package com.socket.tcptest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        //提升作用域【就可以写finally】
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;



        //1. 我得有一个地址
        try {
            serverSocket = new ServerSocket(9999);
            //2. 等待客户端链接过来
            socket = serverSocket.accept();

            //3. 读取客户端的消息
            is = socket.getInputStream();


            /*
            //如果有中文字符，超过1024长度，中文字的代码就会被分割开来，就会产生乱码
            byte[] buffer = new byte[1024];
            int len;
            while((len=is.read(buffer))!= -1){
                String s = new String(buffer, 0, len);
                System.out.println(s);
            }
            */

            //管道流
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;

            //无限长管道，用来接收buffer 中的内容
            while((len=is.read(buffer))!= -1){
                baos.write(buffer, 0, len);
            }
            System.out.println(baos.toString());






        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (baos!= null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (is!= null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socket!= null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
