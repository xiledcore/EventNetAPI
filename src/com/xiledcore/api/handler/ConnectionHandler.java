package com.xiledcore.api.handler;

import java.net.Socket;

/*
 * {@link ConnectionHandler} is an abstract class which the
 * user of this API will write a subclass of, when specifying their own handler for incoming connections.
 * 
 * @author Xiledcore
 */
public abstract class ConnectionHandler {
	
	/*
	 * The abstract method which will handle incoming connections.
	 */
	public abstract void handleConnection(Socket socket);
}
