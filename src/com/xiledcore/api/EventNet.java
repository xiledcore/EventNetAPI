package com.xiledcore.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.xiledcore.api.handler.ConnectionHandler;
import com.xiledcore.api.handler.IncomingPacketHandler;

/*
 * {@link EventNet} is a class that initializes everything along with the {@link EventNetBootstrap} class,
 * using the specified handlers passed in by the user of this API.
 * 
 * @author Xiledcore
 */
public final class EventNet {
	
	/*
	 * The {@link Logger} instance of this class.
	 */
	private static final Logger logger = Logger.getLogger(EventNet.class.getName());
	
	/*
	 * The {@link EventNetBootstrap} object which will be used to initialize everything.
	 */
	private EventNetBootstrap bootstrap;
	
	/*
	 * The {@link ConnectionHandler} instance of this class.
	 */
	private ConnectionHandler connectionHandler;
	
	/*
	 * The {@link IncomingPacketHandler} instance of this class.
	 */
	private IncomingPacketHandler incomingPacketHandler;
	
	/*
	 * The port that will be used.
	 */
	private final int port;
	
	/*
	 * The constructor of this class.
	 */
	private EventNet(int port) {
		this.port = port;
	}
	
	private void init() {
		bootstrap = new EventNetBootstrap(connectionHandler, incomingPacketHandler, port);
	}
	
	/*
	 * Starts the {@link EventNetBootstrap} instance.
	 */
	public void start() {
		bootstrap.start();
		
		logger.log(Level.INFO, "EventNet has started up!");
	}
	
	/*
	 * Sets the {@link ConnectionHandler} instance of this class.
	 * 
	 * @param connectionHandler The {@link ConnectionHandler} instance.
	 */
	private void setConnectionHandler(ConnectionHandler connectionHandler) {
		this.connectionHandler = connectionHandler;
	}
	
	/*
	 * Sets the {@link IncomingPacketHandler} instance of this class.
	 * 
	 * @param incomingPacketHandler The {@link IncomingPacketHandler} instance.
	 */
	private void setIncomingPacketHandler(IncomingPacketHandler incomingPacketHandler) {
		this.incomingPacketHandler = incomingPacketHandler;
	}
	
	/*
	 * The builder class, used for creating an {@link EventNet} instance.
	 */
	public static class Builder {
		
		/*
		 * The {@link ConnectionHandler} instance of this class.
		 */
		private ConnectionHandler connectionHandler;
		
		/*
		 * The {@link IncomingPacketHandler} instance of this class.
		 */
		private IncomingPacketHandler incomingPacketHandler;
		
		/*
		 * The port that will be binded to the {@link ServerSocket} instance of the
		 * {@link EventNetBootstrap} instance.
		 */
		private int port;
		
		/*
		 * Sets the {@link ConnectionHandler} instance of this class.
		 * 
		 * @param connectionHandler The {@link ConnectionHandler} instance.
		 */
		public Builder setConnectionHandler(ConnectionHandler connectionHandler) {
			this.connectionHandler = connectionHandler;
			return this;
		}
		
		/*
		 * Sets the {@link IncomingPacketHandler} instance of this class.
		 * 
		 * @param incomingPacketHandler The {@link IncomingPacketHandler} instance.
		 */
		public Builder setPacketHandler(IncomingPacketHandler incomingPacketHandler) {
			this.incomingPacketHandler = incomingPacketHandler;
			return this;
		}
		
		/*
		 * Sets the port.
		 * 
		 * @param port The port.
		 */
		public Builder setPort(int port) {
			this.port = port;
			return this;
		}
		
		/*
		 * Creates the {@link EventNet} instance.
		 */
		public EventNet build() {
			if (port == 0) {
				throw new IllegalArgumentException("Port must be set!");
			}
			
			if(connectionHandler == null || incomingPacketHandler == null) {
				throw new IllegalArgumentException("Both the connection handler and the packet handler must be set!");
			}
			
			EventNet eventNet = new EventNet(port);
			eventNet.setConnectionHandler(connectionHandler);
			eventNet.setIncomingPacketHandler(incomingPacketHandler);
			eventNet.init();
			
			return eventNet;
		}
	}
}
