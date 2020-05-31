package com.socket.urltest;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {
    public static void main(String[] args) throws Exception {
        //1-下载地址
        URL url = new URL("https://i.ytimg.com/an/upvZG-5ko_eiXAupbDfxWw/featured_channel.jpg?v=5c463346");
        //2-连接到这个资源的http、

        //打开闸门
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

        //引入资源
        InputStream inputStream = urlConnection.getInputStream();

        FileOutputStream fos = new FileOutputStream("cnn.jpg");

        byte[] buffer = new byte[1024];
        int len;
        while((len=inputStream.read(buffer))!=-1){
            fos.write(buffer,0,len); //写出这个数据
        }

        fos.close();
        inputStream.close();
        urlConnection.disconnect();



    }
}
