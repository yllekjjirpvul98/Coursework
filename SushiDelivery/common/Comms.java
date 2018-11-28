/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import client.Client;
import common.Message.MessageType;
import server.Server;

public class Comms{
//handle communication between the business application and the client application

	static Comms com = new Comms();
	
	SocketServer server;
	SocketClient client;
	
	public static Comms getCommunicationLayer() {
		return com;
	}
	
	//run the socket server thread to listen to socket client
	public void runServer() {
		server = new SocketServer();
		new Thread(server).start();
	}
	
	//run the client server thread to receive server response
	public void runClient() {
		client = new SocketClient();
		Thread thread = new Thread(client);
		thread.start();
		thread.setPriority(Thread.MAX_PRIORITY);
	}
	
	//send message from client to server
	public void clientSendMessage(Message message) {
		client.sendMessage(message);
	}
		
	//send message from server to client
	public void serverSendMessage(Message message) {
		server.sendMessage(message);
	}
	
	//wait for response from the server
	public Object receiveMessage() {
		Object message = null;
		while (true) {
			try {
				//wait for 50ms for the message to arrive
				Thread.sleep(50);
				if (client.checkMailbox()) {
					message = client.receiveMessage();
					System.out.println("Message recieved:" + message);
					break;
				}else {
					continue;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		return message;
	}
	
	
	
	
	
}

