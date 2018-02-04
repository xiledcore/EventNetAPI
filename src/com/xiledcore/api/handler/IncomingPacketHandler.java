package com.xiledcore.api.handler;

import com.xiledcore.api.model.Packet;

/*
 * {@link IncomingPacketHandler} is an abstract class that the user
 * of this API will write a subclass of, when specifying their own handler for incoming packets.
 * 
 * @author Xiledcore
 */
public abstract class IncomingPacketHandler {
	
	/*
	 * The abstract method for handling packets.
	 */
	public abstract void handlePacket(Packet packet);
}
