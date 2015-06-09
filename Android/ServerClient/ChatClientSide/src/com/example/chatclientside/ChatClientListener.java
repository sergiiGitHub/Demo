package com.example.chatclientside;

public interface ChatClientListener {
	void onUpdateChatMsg( final String msg );
	void onToastMsg( final String msg );
	void onDisconnected();
}
