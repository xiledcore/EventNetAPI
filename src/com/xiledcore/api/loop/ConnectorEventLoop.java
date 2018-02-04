package com.xiledcore.api.loop;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.xiledcore.api.handler.ConnectionHandler;

/*
 * {@link ConnectorEventLoop} is a class that repeatedly listens for
 * new incoming connections, so that the user-specified connection handler can handle the incoming connection appropriately.
 * 
 * @author Xiledcore
 */
public final class ConnectorEventLoop implements Runnable {
	
	/*
	 * The {@link Logger} instance of this class.
	 */
	private static final Logger logger = Logger.getLogger(ConnectorEventLoop.class.getName());
	
	/*
	 * The {@link ConnectionHandler} instance of this class.
	 */
	private final ConnectionHandler connectionHandler;
	
	/*
	 * The {@link ServerSocket} instance of this class.
	 */
	private final ServerSocket socket;
	
	/*
	 * The constructor of this class.
	 * 
	 * @param connectionHandler The connection handler to be passed in.
	 * @param socket The server socket that will be used when listening for incoming connections.
	 */
	public ConnectorEventLoop(ConnectionHandler connectionHandler, ServerSocket socket) {
		this.connectionHandler = connectionHandler;
		this.socket = socket;
	}
	
	/*
	 * The method called when an instance of this class has been submitted to an {@link ExecutorService}.
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Socket connection = socket.accept();
				connectionHandler.handleConnection(connection);
			} catch (IOException e) {
				logger.log(Level.WARNING, "IOException occurred!", e);
			}
		}
	}
}
