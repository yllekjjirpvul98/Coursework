public class EnigmaMachine {
	
	Plugboard plugboard;
	BasicRotor[] basicrotor = new BasicRotor[3];
	//array of character from A to Z
	char[] character = new char[]{ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	//array of integer from 0 to 25
	int[] integerSet = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };
	Reflector r;
	
	//constructor that creates a new plugboard once an EnigmaMachine is created
	public EnigmaMachine() {
		this.plugboard = new Plugboard();
	}
	
	//method that add the plug with the two ends
	public void addPlug(char letterA, char letterB) {
		this.plugboard.addPlug(letterA, letterB);
	}
	
	//method that add a specific rotor to a specific slot
	public void addRotor (BasicRotor rotor, int slot) {
		basicrotor[slot] = rotor;
	}
	
	//method that get the rotor from a specific slot
	public BasicRotor getRotor(int slot) {
		return basicrotor[slot];
		
	}
	
	//method that add the reflector
	public void addReflector(Reflector reflector) {
		r = reflector;
	}
	
	//method that returns the reflector
	public Reflector getReflector() {
		return this.r;
	}
	
	//method that set the position of a rotor in a specific slot
	public void setPosition(int slot, int position) {
		this.getRotor(slot).setPosition(position);
	}
	
	//method the convert letter into number
	public int convertLetter(char character) {
		int output = 0;
		for (int i=0; i < this.character.length; i++) {
			if (this.character[i] == character) {
				output = integerSet[i];
			}
		}return output;
	}
	
	//method that convert number into letter
	public char convertNum(int Integer) {
		char character = ' ';
		for (int i=0; i < integerSet.length; i++) {
			if (integerSet[i] == Integer) {
				character =  this.character[i];	
			}
		}return character;
	}
	
	//method that clear the plugboard
	public void clearPlugboard() {
		this.plugboard.clear();
	}
	
	//method that encode the letter following the pseudocode on the specification
	public char encodeLetter(char character) {
		char output = this.plugboard.substitute(character);
		int numoutput = this.convertLetter(output);
		BasicRotor rotor1 = this.getRotor(0);
		int numoutput2 = rotor1.substitute(numoutput);
		BasicRotor rotor2 = this.getRotor(1);
		int numoutput3 = rotor2.substitute(numoutput2);
		BasicRotor rotor3 = this.getRotor(2);
		int numoutput4 = rotor3.substitute(numoutput3);
		int numoutput5 = this.getReflector().substitute(numoutput4);
		int numoutput6 = rotor3.substituteBack(numoutput5);
		int numoutput7 = rotor2.substituteBack(numoutput6);
		int numoutput8 = rotor1.substituteBack(numoutput7);
		char output2 = this.convertNum(numoutput8);
		char output3 = this.plugboard.substitute(output2);
		System.out.print(output3);
		rotor1.rotate();
		rotor1.setNextRotor(rotor2);
		rotor2.setNextRotor(rotor3);
		if (rotor1 instanceof TurnoverRotor && rotor1.CheckRotateNextRotor()) {
			rotor1.RotateNextRotor();
			if (rotor2 instanceof TurnoverRotor && rotor2.CheckRotateNextRotor()) {
			rotor2.RotateNextRotor();
			}
		}
		return output3;
	}
	
	//method that put the settings of different tests
	public void start() {
		//test1
		addPlug('A', 'M');
		addPlug('G', 'L');
		addPlug('E', 'T');
		addRotor(new BasicRotor("I"), 0);
		addRotor(new BasicRotor("II"), 1);
		addRotor(new BasicRotor("III"), 2);
		setPosition(0, 6);
		setPosition(1, 12);
		setPosition(2, 5);
		addReflector(new Reflector("reflectorI"));
		encodeLetter('G');
		encodeLetter('F');
		encodeLetter('W');
		encodeLetter('I');
		encodeLetter('Q');
		encodeLetter('H');
		//getting the result of BADGER
		
		//test2
		clearPlugboard();
		addPlug('B', 'C');
		addPlug('R', 'I');
		addPlug('A', 'F');
		addPlug('S', 'M');
		addRotor(new BasicRotor("IV"), 0);
		addRotor(new BasicRotor("V"), 1);
		addRotor(new BasicRotor("II"), 2);
		setPosition(0, 23);
		setPosition(1, 4);
		setPosition(2, 9);
		addReflector(new Reflector("reflectorII"));
		encodeLetter('G');
		encodeLetter('A');
		encodeLetter('C');
		encodeLetter('I');
		encodeLetter('G');
		//getting the result of SNAKE
		
		//test3
		clearPlugboard();
		addPlug('Q', 'F');
		addRotor(new TurnoverRotor("I"), 0);
		addRotor(new TurnoverRotor("II"), 1);
		addRotor(new TurnoverRotor("III"), 2);
		setPosition(0, 23);
		setPosition(1, 11);
		setPosition(2, 7);
		addReflector(new Reflector("reflectorI"));
		String stringToDecode = "OJVAYFGUOFIVOTAYRNIWJYQWMXUEJGXYGIFT";
		char[] stringToDecodeCharArray = stringToDecode.toCharArray();
		for (char character:stringToDecodeCharArray) {
			encodeLetter(character);
		}
		//getting the result of THEQUICKBROWNFOXJUMPEDOVERTHELAZYDOG
	}
	
	public static void main(String[] args) {
		EnigmaMachine enigma = new EnigmaMachine();
		enigma.start();
	}
	
}
