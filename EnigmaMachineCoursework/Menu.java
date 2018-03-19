import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Extension to create a class that creates a command line interface to ask for user 
 * to input rotor type, initial position, reflector and inserting plugs
 */
public class Menu {
	EnigmaMachine enigma = new EnigmaMachine();
	
	
	//method that uses a bufferedreader to read an integer from the command line
	public Integer readIntegerFromCmd() throws IOException {
		System.out.println("Enter your number:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String number = null;
		number = br.readLine();
		return new Integer(number);
	}
	
	//method that uses a bufferedreader to read a string from the command line
	public String readStringFromCmd() throws IOException {
		System.out.println("Enter your String:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String string = null;		
		string = br.readLine();
		return string;
	}
	
	//method that print out the menu
	public void PrintMenu() throws IOException {
		//loop the menu until the user chooses to quit
		while (true) {
			System.out.println("Welcome to the EnigmaMachine command interface!");
			System.out.println("Enter 1 to add the plugs");
			System.out.println("Enter 2 to add the rotors");
			System.out.println("Enter 3 to add the reflector");
			System.out.println("Enter 4 to enter the message");
			System.out.println("Enter 5 to quit the menu and start the machine");
			
			int option = this.readIntegerFromCmd();
			//jump to different method according to the input of the user
			if (option == 1) {
				this.addPlug();
			}else if (option == 2) {
				this.addRotors();
			}else if (option == 3) {
				this.addReflector();
			}else if (option == 4) {
				this.addMessage();
			}else if (option == 5) {
				break;
			}
		}
	}
	
	//method that take in the ends of the plug and add the plug to the plugboard
	public void addPlug() throws IOException {
		System.out.println("Enter one end of the plug");
		String end1 = this.readStringFromCmd();
		char end1_char = end1.charAt(0);
		System.out.println("Enter another end of the plug");
		String end2 = this.readStringFromCmd();
		char end2_char = end2.charAt(0);
		enigma.addPlug(end1_char, end2_char);
		//if user has more plugs to input then run the method again(recursion)
		System.out.println("Do you have more plugs to input? Answer Y or N");
		String option = this.readStringFromCmd();
		if (option.equals("Y")) {
			this.addPlug();
		}
	}
	
	//method that take in the rotor type and initial position of the rotors
	public void addRotors() throws IOException {
		System.out.println("Enter the rotor type for slot 0");
		String type0 = this.readStringFromCmd();
		System.out.println("Enter the initial position of slot 0");
		int position0 = this.readIntegerFromCmd();
		System.out.println("Enter the rotor type for slot 1");
		String type1 = this.readStringFromCmd();
		System.out.println("Enter the initial position of slot 1");
		int position1 = this.readIntegerFromCmd();
		System.out.println("Enter the rotor type for slot 2");
		String type2 = this.readStringFromCmd();
		System.out.println("Enter the initial positon of slot 2");
		int position2 = this.readIntegerFromCmd();
		
		enigma.addRotor(new BasicRotor(type0), 0);
		enigma.addRotor(new BasicRotor(type1), 1);
		enigma.addRotor(new BasicRotor(type2), 2);
		enigma.setPosition(0, position0);
		enigma.setPosition(1, position1);
		enigma.setPosition(2, position2);
	}
	
	//method that add the reflector to the enigma machine
	public void addReflector() throws IOException {
		System.out.println("Enter the reflector type you want to add");
		String type = this.readStringFromCmd();
		
		enigma.addReflector(new Reflector("reflector"+type));
	}
	
	//method that return the enigma machine
	public EnigmaMachine getEnigmaMachine() {
		return enigma;
	}
	
	String message;
	//method that allow user to add to type in the message to be decrypted 
	public void addMessage() throws IOException {
		System.out.println("Enter the message to be decrypted");
		message = this.readStringFromCmd();
	}
	
	//method that return user's message
	public String getMessage() {
		return message;
	}
	
	public static void main(String[] args) throws IOException {
		
		Menu menu = new Menu();
		menu.PrintMenu();
		
		EnigmaMachine enigma = menu.getEnigmaMachine();
		
		//loop through each character in the message given by the user
		for (char character:menu.getMessage().toCharArray()){
			enigma.encodeLetter(character);
		}

	}
	
	
}
