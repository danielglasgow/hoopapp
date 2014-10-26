package com.example.helloworld;

public class ServerConnector {
	
	private static final ServerInterface connection = new ServerConnectionProxy();
	
	public static ServerInterface getInstance() {
		return connection;
	}

}
