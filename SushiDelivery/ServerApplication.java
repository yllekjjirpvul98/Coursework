/* Created by Ching Man Yung, 17/5/2018
 * 
 */

import server.Server;
import server.ServerWindow;

public class ServerApplication{
	
	//initialise the server by creating a new instance
	public Server initialise() {
		Server server = new Server();
		return server;
	}
	
	//launch the GUI window by creating a server window
	public void launchGUI(Server server) {
		ServerWindow window = new ServerWindow(server);
	}
	
	public void main() {
		launchGUI(initialise());
	}
	
	//main method to run the server application
	public static void main(String[] args) {
		ServerApplication server = new ServerApplication();
		server.main();
	}
	
	
}
