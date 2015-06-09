package com.example.chatclientside;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//TODO refactor
public class ChatClientSide extends Activity implements ChatClientListener, OnClickListener {

	static final int SocketServerPORT = 8080;

	LinearLayout loginPanel, chatPanel;

	EditText editTextUserName, editTextAddress;
	Button buttonConnect;
	TextView chatMsg, textPort;

	EditText editTextSay;
	Button buttonSend;
	Button buttonDisconnect;

	ChatClientThread chatClientThread = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat_client_side);

		
		//TODO refactor
		loginPanel = (LinearLayout)findViewById(R.id.loginpanel);
		chatPanel = (LinearLayout)findViewById(R.id.chatpanel);

		editTextUserName = (EditText) findViewById(R.id.username);
		editTextAddress = (EditText) findViewById(R.id.address);
		editTextAddress.setText("192.168.0.102");
		textPort = (TextView) findViewById(R.id.port);
		textPort.setText("port: " + SocketServerPORT);
		buttonConnect = (Button) findViewById(R.id.connect);
		buttonDisconnect = (Button) findViewById(R.id.disconnect);
		chatMsg = (TextView) findViewById(R.id.chatmsg);

		buttonConnect.setOnClickListener(this);
		buttonDisconnect.setOnClickListener(this);

		editTextSay = (EditText)findViewById(R.id.say);
		
		buttonSend = (Button)findViewById(R.id.send);
		buttonSend.setOnClickListener( this );
	}

	private void connected(){
		String textUserName = editTextUserName.getText().toString();
		if (textUserName.equals("")) {
			Toast.makeText(ChatClientSide.this, "Enter User Name",
					Toast.LENGTH_LONG).show();
			return;
		}

		String textAddress = editTextAddress.getText().toString();
		if (textAddress.equals("")) {
			Toast.makeText(ChatClientSide.this, "Enter Addresse",
					Toast.LENGTH_LONG).show();
			return;
		}

		loginPanel.setVisibility(View.GONE);
		chatPanel.setVisibility(View.VISIBLE);

		chatClientThread = new ChatClientThread( textUserName, 
				textAddress,
				SocketServerPORT,
				this);
		chatClientThread.start();
	}
	
	private void send() {
		if (editTextSay.getText().toString().equals("")) {
			return;
		}

		if(chatClientThread==null){
			return;
		}

		chatClientThread.sendMsg(editTextSay.getText().toString() + "\n");
	}

	@Override
	public void onClick(View aView) {
		switch (aView.getId()) {
		case R.id.connect:
			connected();
			break;
		case R.id.disconnect:
			chatClientThread.disconnect();
			break;
		case R.id.send:
			send( );
			break;			
		default:
			break;
		}

	}
	
	@Override
	public void onUpdateChatMsg(final String msg) {
		ChatClientSide.this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				chatMsg.setText(msg);
			}
		});
	}

	@Override
	public void onToastMsg(final String msg) {
		ChatClientSide.this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(ChatClientSide.this, msg, Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public void onDisconnected() {
		ChatClientSide.this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				loginPanel.setVisibility(View.VISIBLE);
				chatPanel.setVisibility(View.GONE);
			}
		});
	}

}
