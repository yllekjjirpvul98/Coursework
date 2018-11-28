/* Created by Ching Man Yung, 17/5/2018
 * 
 */

import client.Client;
import client.ClientWindow;


public class ClientApplication{
	
	//initialise client by creating new Client instance
	public Client initialise() {
		Client client = new Client();
		return client;
	}
	
	//launch GUI by creating a new client window
	public void launchGUI(Client client) {
		ClientWindow window = new ClientWindow(client);
	}
	
	public void main() {
		Client client = initialise();
		launchGUI(client);
	}
	
	//main method to launch the client application
	public static void main(String[] args) {
		ClientApplication client = new ClientApplication();
		client.main();
	}


}
