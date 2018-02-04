package com.xiledcore.api.loop;

import com.xiledcore.api.handler.IncomingPacketHandler;

/*
 * {@link IncomingPacketLoop} is a class that repeatedly listens for
 * new incoming packets, so that the user-specified packet handler can handle the incoming packets appropriately.
 * 
 * @author Xiledcore
 */
public final class IncomingPacketLoop implements Runnable {
	
	/*
	 * The {@link IncomingPacketHandler} instance of this class.
	 */
	private final IncomingPacketHandler packetHandler;
	
	/*
	 * The constructor of this class.
	 * 
	 * @param packetHandler The packet handler that is specified by the user of this API.
	 */
	public IncomingPacketLoop(IncomingPacketHandler packetHandler) {
		this.packetHandler = packetHandler;
	}
	
	/*
	 * The method called when an instance of this class has been submitted to an {@link ExecutorService}.
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
	}
	
}
