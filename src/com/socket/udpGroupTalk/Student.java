package com.socket.udpGroupTalk;

public class Student {
    public static void main(String[] args) {
        //开启两个线程
        new Thread(new TalkSend("Student",7000, 8001,"localhost")).start();
        new Thread(new TalkReceive(7001,"Teacher")).start();

    }
}
