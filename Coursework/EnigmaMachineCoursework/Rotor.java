//Rotor is abstract so it cannot be instantiated 
//it is the super class of BasicRotor and Reflector
public abstract class Rotor {

	String name;
	int position;
	int[] mapping;
	Integer ROTORSIZE = 26;
	
	//method to set the position of the rotor(BasicRotor)
	public void setPosition(int position) {
		this.position = position;
	}
	
	//method to get the position of the rotor(BasicRotor)
	public int getPosition() {
		return this.position;
	}
	
	//abstract method to be used in the subclass(BasicRotor,Reflector)
	public void initialise(String string) {
		
	}
	
	//abstract method to be used in the subclass(BasicRotor, Reflector)
	public int substitute(int integer) {
		return integer;
	}
	
	//abstract method to be used in the subclass(TurnoverRotor)
	public boolean CheckRotateNextRotor() {
		return false;
	}
	
	//abstract method to be used in the subclass (TurnoverRotor)
	public void setNextRotor(BasicRotor rotor) {
		
	}
	
	//abstract method to be used in the subclass(TurnoverRotor)
	public void RotateNextRotor() {
	}
}
