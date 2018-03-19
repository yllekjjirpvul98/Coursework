public class Plugboard {
	//plugarray should only have 13 plugs at maximum
	public Plug[] plug_array = new Plug[13];
	public Plug plug;
	
	//method to add plug to the plugboard
	public boolean addPlug(char End1, char End2) {
		plug = new Plug(End1, End2);
		boolean status = false;
		for (int i=0; i < 13; i++) {
			//if the position is empty, then add plug and return true
			if (plug_array[i]== null) {
				status = true;
				//add plug to the empty position
				plug_array[i] = plug;
				break;
			//if the plug clashes with existing plug then return false
			}else if(plug.clashesWith(plug_array[i])) {
					status = false;
					break;
			}
		}return status;
	}
	
	//method to get number of plugs
	public int getNumPlugs() {
		int NumOfPlugs = 0;
		for (int i=0; i < 13; i++) {
			if (plug_array == null) {
				break;
			}
			//if the position is not null then increment number of plugs by one
			else if (plug_array[i] != null) {
				NumOfPlugs += 1;
			}
		}return NumOfPlugs;
	}
	
	//method to clear the plugboard
	public void clear() {
		//by creating a new plug array 
		this.plug_array = new Plug[13];
	}
	
	//method to substitute character
	public char substitute(char character) {
		char output = character;
		for (int i=0; i < this.getNumPlugs(); i++) {
			//if the character equal to one of the end of the plugs inside the plug array
			if (plug_array[i].getEnd1() == character || plug_array[i].getEnd2() == character) {
				output = plug_array[i].encode(character);
				break;
			}
		}return output;
	}

}
