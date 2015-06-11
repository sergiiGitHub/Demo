package com.java.clientserver.serverside;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.java.clientserver.serverside.ConnectThread.IConnectedThreadListener;

public class ChatServerThread extends Thread implements IConnectedThreadListener {

    static final int SocketServerPORT = 8080;
    
    final private List< ConnectThread > userList;

    private ServerSocket serverSocket = null;
    private Socket socket = null;

    public ChatServerThread() {
    	userList = new ArrayList<ConnectThread>();
	}

    @Override
    public void run() {
    	
        try {
            serverSocket = new ServerSocket( SocketServerPORT );
            //TODO on print
            System.out.println("I'm waiting here: "
                + serverSocket.getLocalPort());
                System.out.println("CTRL + C to quit");

            while (true) {
                socket = serverSocket.accept();
                ConnectThread connectThread = new ConnectThread( socket );
                connectThread.setConnectedThreadListener(this);
                connectThread.start();
                
                //TODO implement it 
//                try {
//					wait(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	closeSocket();
        }
    }
   
	private void closeSocket() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

	
	@Override
	public void onAddItem(ConnectThread aConnectThread) {
		userList.add(aConnectThread);
	}


	@Override
	public void onRemoveItem(ConnectThread aConnectThread) {
		userList.remove(aConnectThread);
		
		String msg = "-- " + aConnectThread.getClientName() + " leaved\n";
		onPrintLog(msg);
		onBroadcastMsg(msg);
	}

	@Override
	public void onBroadcastMsg(String aMsg) {
        for (int i = 0; i < userList.size(); i++) {
            userList.get(i).sendMsg(aMsg);
            System.out.print("- send to " + userList.get(i).getClientName() + "\n");
        }
        System.out.println();
	}

	@Override
	public void onPrintLog(String aMsg) {
		System.out.print( aMsg );
	}

	
}


