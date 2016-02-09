package com.java.clientserver.serverside;


public class JavaCmdChatServer {

    String msgLog = "";
   
    public static void main(String[] args) {
    	ChatServerThread chatServerThread = new ChatServerThread();
    	System.out.print(chatServerThread.getIpAddress());
        chatServerThread.start();
        try {
			chatServerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

}
