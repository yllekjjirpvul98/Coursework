//Reflector is a subclass of Rotor
public class Reflector extends Rotor {
	
	//constrcutor that takes in the name of Reflector
	public Reflector(String name) {
		this.name = name;
		//create mapping according to the name of the reflector
		this.initialise(this.name);
		
	}
	
	//initialise different mapping according to type of reflector
	public void initialise(String type) {
		if (type.equals("reflectorI")) {
			int[] mapping = new int[] { 24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19 };
			this.mapping = mapping;
		}else if (type.equals("reflectorII")) {
			int[] mapping = new int[] { 5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11 };
			this.mapping = mapping;
		}	
	}
	
	//method to map a character to another character
	public int substitute(int integer) {
		return mapping[integer];
	}
	

}

