public class Plug {

	char End1;
	char End2;
	
	//constructor that take in both ends of the plug
	public Plug(char End1, char End2) {
		this.End1 = End1;
		this.End2 = End2;
	}
	
	//getter that returns end1
	public char getEnd1() {
		return this.End1;
	}
	
	//getter that returns end2
	public char getEnd2() {
		return this.End2;
	}
	
	//setter that set the value of end1
	public void setEnd1(char character) {
		this.End1 = character;
	}
	
	//setter that set the value of end2
	public void setEnd2(char character) {
		this.End2 = character;
	}
	
	//method that encodes a letter
	public char encode(char letterIn) {
		//if end1 equals to letterin then returns the character of opposite end, vice versa
		if (this.End1 == letterIn) {
			return this.getEnd2();
		}else if (this.End2 == letterIn) {
			return this.getEnd1();
		}else {
			return letterIn;	
		}
	}
	
	//method that check whether the plug passed clashes with existing plugs on the plugboard
	public boolean clashesWith(Plug plugin) {
		if (plugin.getEnd1() == this.End1 || plugin.getEnd2() == this.End2 || plugin.getEnd1() == this.End2 || plugin.getEnd2() == this.End1){
			return true;
		}else return false;
	}

}
	
