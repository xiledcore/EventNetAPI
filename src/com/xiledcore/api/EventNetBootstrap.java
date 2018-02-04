package com.xiledcore.api;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.xiledcore.api.handler.ConnectionHandler;
import com.xiledcore.api.handler.IncomingPacketHandler;
import com.xiledcore.api.loop.ConnectorEventLoop;

/*
 * {@link EventNetBootstrap} is a class that instantiates the resources that the loops
 * will need to listen for incoming connections and incoming packets.
 * 
 * @author Xiledcore
 */
public final class EventNetBootstrap {
	
	/*
	 * The {@link Logger} instance of this class.
	 */
	private static final Logger logger = Logger.getLogger(EventNetBootstrap.class.getName());
	
	/*
	 * The {@link ServerSocket} instance of this class.
	 */
	private ServerSocket socket;
	
	/*
	 * The {@link ExecutorService} instance of this class.
	 */
	private final ExecutorService looperService = Executors.newFixedThreadPool(2);
	
	/*
	 * The {@link AbstractConnectionHandler} instance of this class.
	 */
	private ConnectionHandler connectionHandler;
	
	/*
	 * The {@link AbstractIncomingPacketHandler} instance of this class.
	 */
	private IncomingPacketHandler incomingPacketHandler;
	
	/*
	 * The constructor of this class.
	 * 
	 * @param connectionHandler The {@link ConnectionHandler} instance.
	 * @param incomingPacketHandler The {@link IncomingPacketHandler} instance.
	 */
	public EventNetBootstrap(ConnectionHandler connectionHandler, IncomingPacketHandler incomingPacketHandler, int port) {
		this.connectionHandler = connectionHandler;
		this.incomingPacketHandler = incomingPacketHandler;
		
		bind(port);
	}
	
	/*
	 * Binds the {@link ServerSocket} instance to the specified port.
	 * 
	 * @param port The port to bind the {@link ServerSocket} to.
	 */
	public void bind(int port) {
		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Could not instantiate ServerSocket instance!", e);
		}
	}
	
	/*
	 * Starts the looper service.
	 */
	public void start() {
		looperService.submit(new ConnectorEventLoop(connectionHandler, socket));
	}
}
