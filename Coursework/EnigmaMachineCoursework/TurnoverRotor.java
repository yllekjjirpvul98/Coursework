//TurnoverRotor extends from BasicRotor
public class TurnoverRotor extends BasicRotor {
	
	int turnoverPosition;
	int typeI = 24;
	int typeII = 12;
	int typeIII = 3;
	int typeIV = 17;
	int typeV = 7;
	BasicRotor nextRotor;
	
	//constructor that take in the type
	public TurnoverRotor(String type) {
		super(type);
		//set different turn over position according to the type of rotor
		if (type.equals("I")) {
			turnoverPosition = typeI;
		}else if (type.equals("II")) {
			turnoverPosition = typeII;
		}else if (type.equals("III")) {
			turnoverPosition = typeIII;
		}else if (type.equals("IV")) {
			turnoverPosition = typeIV;
		}else if (type.equals("V")) {
			turnoverPosition = typeV;
		}
	}
	
	//setter that set next rotor to the input rotor
	public void setNextRotor(BasicRotor rotor) {
		this.nextRotor = rotor;
	}
	
	//method to check whether they should rotate the next rotor 
	public boolean CheckRotateNextRotor() {
		boolean output = false;
		if (this.position == turnoverPosition){
			output = true;
		}else {
			output = false;
		}return output;
	}
	
	//method to rotate the next rotor
	public void RotateNextRotor() {
		nextRotor.rotate();
	}
}
