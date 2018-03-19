//BasicRotor is the subclass of the Rotor
public class BasicRotor extends Rotor{
	
	//constructor that take in the type of the rotor
	public BasicRotor(String type) {
		this.name = type;
		this.initialise(this.name);
	}
	
	//method to initialise the mapping 
	public void initialise(String type) {
		if (type.equals("I")) {
			this.mapping = new int[]{ 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };
		}else if (type.equals("II")) {
			this.mapping = new int[] { 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 };
		}else if (type.equals("III")) {
			this.mapping = new int[] { 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 };
		}else if (type.equals("IV")) {
			this.mapping = new int[]  {4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1 }; ;
		}else if (type.equals("V")) {
			this.mapping = new int[] { 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 };
		}
	}
	
	//method to substitute the input letter using the rotor
	public int substitute(int letter) {
		int sub;
		letter = letter - position;
		//if letter smaller than 0 add 26 to it
		//treat it as a circular queue
		if (letter < 0) {
			letter = 26 + letter;
		}
		sub = mapping[letter];//map the letter using the mapping
		sub = (sub + position);//add the position back after mapping
		if (sub > 25) {
			sub = sub-26;
		}
		return sub;
	}
	
	//method to substitute back
	public int substituteBack(int letter) {
		int[] inverseMapping = new int[mapping.length];
		int sub;
		
		//build an inversemapping
		for (int i=0; i < mapping.length; i++ ) {
			inverseMapping[mapping[i]] = i;
		}
		
		letter = letter - position;
		if (letter < 0) {
			letter = 26 + letter;
		}
		sub = inverseMapping[letter];//map the letter using the inversemapping
		sub = (sub + position);
		if (sub > 25) {
			sub = sub-26;
		}
		return sub;
	}
	
	//method to rotate the rotor
	public void rotate() {
		//if the position reach 25, the next position should be back to 0
		if (position == ROTORSIZE-1) {
			position = 0;
		}else {
			//increment the position by 1
			position += 1;
		}
	}
	
}
