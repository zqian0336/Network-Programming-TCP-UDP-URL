package com.socket.udpGroupTalk;

public class Teacher {
    public static void main(String[] args) {
        new Thread(new TalkSend("Teacher",8000,7001,"localhost")).start();
        new Thread(new TalkReceive(8001,"student")).start();
    }
}
