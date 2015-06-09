package com.example.serverside.chat;

public interface ConnectedThreadListener {
	void onRemove( ChatClient aClient );
	void broadcastMsg(String  msg);

}
