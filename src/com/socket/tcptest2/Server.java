package com.socket.tcptest2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {

        //1 - 创建服务
        ServerSocket serverSocket = new ServerSocket(9000);

        //2- 监听客户端的连接
        Socket socket = serverSocket.accept();//阻塞式监听，辉一只等待客户端连接

        //3 - 获取输入流
        InputStream is = socket.getInputStream();

        //4 - 文件输出
        FileOutputStream fos = new FileOutputStream(new File("receive.txt"));
        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer))!= -1){
            fos.write(buffer, 0, len);
        }

        //通知客户端我接收完毕了
        OutputStream os = socket.getOutputStream();
        os.write("I get you file!".getBytes());

        fos.close();
        is.close();
        socket.close();
        serverSocket.close();
    }
}
