/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SocketClient implements Runnable{
		public static final int LISTEN_PORT = 1111;
		Socket clientSocket = null;
		static Object object = null;
		ObjectInputStream clientIn;
		ObjectOutputStream clientOut;
		static Object lock = new Object();
		ArrayList<Object> mailbox = new ArrayList<Object>();
		
		SocketClient(){
			System.out.println("Client trying to connect to the host...");
			try {
				clientSocket = new Socket("127.0.0.1", LISTEN_PORT);
				clientSocket.setReuseAddress(true);
				clientIn = new ObjectInputStream(clientSocket.getInputStream());
				clientOut = new ObjectOutputStream(clientSocket.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/* Client send message by writing object 
		 * to server
		 */
		public void sendMessage(Message message) {
			try {
				clientOut.flush();
				clientOut.reset();
				clientOut.writeObject(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public Object getObject() {
				return object;
		}
		
		public boolean checkMailbox() {
			return (mailbox.size()  != 0);
		}
		
		/* Try to receive message if the mailbox contains
		 * mail and remove the first item as the message 
		 * from the mail queue
		 */
		public Object receiveMessage(){
			Object returnObj = null;
			if (mailbox.size() > 0) {
				returnObj = mailbox.get(0);
				mailbox.remove(0);
			}
			return returnObj;
		}
		
		/* If the message is a String, print the message to stdout;
		 * else add the message (which is an object) to the mailbox
		 */
		public void run() {
			// TODO Auto-generated method stub
				try {
					while (true) {
						for (Object message; (message = clientIn.readObject()) != null;) {
							if (message.getClass() == String.class) {
									if (message.equals("null")) {
										System.out.println("Null mail");
										mailbox.add(null); 
										Thread.sleep(100);
									}else {
										System.out.println(message);
									}
							}else {
								mailbox.add(message);
								Thread.sleep(100);							}	
						}Thread.sleep(100);
					}
					
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						clientSocket.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}	
	}