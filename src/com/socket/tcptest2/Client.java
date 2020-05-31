package com.socket.tcptest2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//读取文件
public class Client {
    public static void main(String[] args) throws Exception {
        //1- 创建socket
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9000);
        //2- 创建输出流
        OutputStream os = socket.getOutputStream();

        //3 - 读取文件
        FileInputStream fis = new FileInputStream(new File("hello.txt"));

        //4 - 写出文件
        byte[] buffer = new byte[1024];
        int len;

        //receive (read) from stream
        while ((len=fis.read(buffer))!= -1){

            //send to os
            os.write(buffer, 0, len);
        }
        //通知服务器，我已经传输完了
        socket.shutdownOutput();

        //确定服务器接收完毕，才能断开连接
        InputStream is = socket.getInputStream();

        //因为我不知道会接收到什么，不用直接用string接收，所以要用管道流
        //String byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer2 = new byte[1024];
        int len2;
        while((len2=is.read(buffer2))!= -1){
            baos.write(buffer2,0,len2);
        }
        System.out.println(baos.toString());

        //5- 关闭资源【先写后关】
        baos.close();
        is.close();
        fis.close();
        os.close();
        socket.close();
    }
}
