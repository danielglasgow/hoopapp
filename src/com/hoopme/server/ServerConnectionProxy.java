package com.hoopme.server;


public class ServerConnectionProxy {
	
	private static final ServerInterface connection = new ServerConnection();//new ServerConnectionProxy();
	
	public static ServerInterface getInstance() {
		return connection;
	}

}
