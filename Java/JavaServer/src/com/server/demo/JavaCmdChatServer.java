package com.server.demo;


public class JavaCmdChatServer {
	public static void main(String[] args) {
		System.out.print("main");
		
	    String msgLog = "";
	    
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
