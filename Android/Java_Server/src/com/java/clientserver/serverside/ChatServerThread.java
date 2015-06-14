package com.java.clientserver.serverside;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
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
    
    public String getIpAddress() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
                .getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = enumNetworkInterfaces
                    .nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface
                    .getInetAddresses();
                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress.nextElement();

                    if (inetAddress.isSiteLocalAddress()) {
                        ip += "SiteLocalAddress: "
                            + inetAddress.getHostAddress() + "\n";
                    }

                }

            }

        } catch (SocketException e) {
            e.printStackTrace();
            ip += "Something Wrong! " + e.toString() + "\n";
        }

        return ip;
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
            	System.out.println( "tik" );
                socket = serverSocket.accept();
                ConnectThread connectThread = new ConnectThread( socket );
                connectThread.setConnectedThreadListener(this);
                connectThread.start();

                waitServer();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	closeSocket();
        }
    }
   
	private void waitServer() {
		synchronized (this) {
			try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
        onBroadcastMsg( aConnectThread.getClientName() + " join our chat.\n");

	}

	@Override
	public void onRemoveItem(ConnectThread aConnectThread) {
		userList.remove(aConnectThread);
		
		String msg = "-- " + aConnectThread.getClientName() + " leaved\n";
		onBroadcastMsg(msg);
	}

	@Override
	public void onBroadcastMsg(String aMsg) {
		System.out.println( "*************************************");
		printLog( aMsg );
        for (int i = 0; i < userList.size(); i++) {
            userList.get(i).sendMsg(aMsg);
            printLog("- send to " + userList.get(i).getClientName() + "\n");
        }
        System.out.println( "*************************************");
	}

	private void printLog(String aMsg) {
		System.out.println( aMsg );
	}

}
