package com.xiledcore.examples;

import java.net.Socket;

import com.xiledcore.api.EventNet;
import com.xiledcore.api.handler.ConnectionHandler;
import com.xiledcore.api.handler.IncomingPacketHandler;
import com.xiledcore.api.model.Packet;

public final class BasicServer {
	
	public static void main(String[] args) {
		EventNet.Builder builder = new EventNet.Builder();
		
		builder.setConnectionHandler(new ConnectionHandler() {

			@Override
			public void handleConnection(Socket socket) {
				System.out.println("Connection noticed! "+socket.getPort());
			}
			
		});
		builder.setPacketHandler(new IncomingPacketHandler() {
			
			@Override
			public void handlePacket(Packet packet) {
				System.out.println("Packet received!");
			}
		});
		builder.setPort(7000);
		
		EventNet server = builder.build();
		server.start();
	}
}