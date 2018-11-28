/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class SocketServer implements Runnable{
		public static final int LISTEN_PORT = 1111;
		ServerSocket serverSocket = null;
		Message messageOut = null;
		ObjectOutputStream serverOut;
		ObjectInputStream serverIn;
		
		public SocketServer(){
			System.out.println("Trying to connect to the client...");
			try {
				/* Establish a server socket to listen to 
				 * client connection and request
				 */
				serverSocket = new ServerSocket(LISTEN_PORT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				serverSocket.setReuseAddress(true);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/* Receive message by reading the object from the
		 * ObjectInputStream
		 */
	
		public Message receiveMessage(ObjectInputStream in) {
			try {
				Message message = (Message) in.readObject();
				return message;
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				return null;
			}	
		}
		
		/* Send message by writing object to the server object output stream
		 */
		public void sendMessage(Message message) {
			try {
				serverOut.writeObject(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public void run() {
			// TODO Auto-generated method stub
				try {
					while (true) {
						Socket connection = serverSocket.accept();
						System.out.println("Client successfully connected");
						new RequestHandler(connection).start();
					}
				}catch (IOException e) {
					// TODO Auto-generated catch block
					try {
						serverSocket.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		}	
	}