import java.io.IOException;

public class Bombe {
	
	public static void main(String args[]){
		EnigmaMachine enigma = new EnigmaMachine();
		

		//Challenge 1
		char[] character = new char[] {'A', 'B', 'C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		for (char letterB:character) {
			for (char letterC:character) {
				enigma.clearPlugboard();
				enigma.addPlug('D', letterB);
				enigma.addPlug('S', letterC);
				enigma.addRotor(new BasicRotor("IV"), 0);
				enigma.addRotor(new BasicRotor("III"), 1);
				enigma.addRotor(new BasicRotor("II"), 2);
				enigma.setPosition(0, 8);
				enigma.setPosition(1, 4);
				enigma.setPosition(2, 21);
				enigma.addReflector(new Reflector("reflectorI"));
				String EncodedMessage = "JBZAQVEBRPEVPUOBXFLCPJQSYFJI";
				char[] EncodedMessageArray = EncodedMessage.toCharArray();
				for (char characters:EncodedMessageArray) {
					enigma.encodeLetter(characters);
				}System.out.println(" When " +letterB+ " and " + letterC);
			}
		} 
		
		//Challenge2
		for (int i=0; i < 26; i++) {
			for (int ii=0; ii < 26; ii++) {
				for (int iii=0; iii < 26; iii++) {
					enigma.clearPlugboard();
					enigma.addPlug('H', 'L');
					enigma.addPlug('G', 'P');
					enigma.addRotor(new BasicRotor("V"), 0);
					enigma.addRotor(new BasicRotor("III"), 1);
					enigma.addRotor(new BasicRotor("II"), 2);
					enigma.setPosition(0, i);
					enigma.setPosition(1, ii);
					enigma.setPosition(2, iii);
					enigma.addReflector(new Reflector("reflectorI"));
					String EncodedMessage = "AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZTGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD";
					char[] EncodedMessageArray = EncodedMessage.toCharArray();
					for (char characters:EncodedMessageArray) {
						enigma.encodeLetter(characters);
					}System.out.println("When in position" + i + " " +ii + " "+iii);
				}
			}
		}
		
		//Challenge 3
		String[] type = new String[] {"I", "II", "III", "IV", "V"};
		for (String type0:type) {
			for (String type1:type) {
				for(String type2: type) {
					enigma.clearPlugboard();
					enigma.addPlug('M', 'F');
					enigma.addPlug('O', 'I');
					enigma.addRotor(new BasicRotor(type0), 0);
					enigma.addRotor(new BasicRotor(type1), 1);
					enigma.addRotor(new BasicRotor(type2), 2);
					enigma.setPosition(0, 22);
					enigma.setPosition(1, 24);
					enigma.setPosition(2, 23);
					enigma.addReflector(new Reflector("reflectorI"));
					String EncodedMessage = "WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW";
					char[] EncodedMessageArray = EncodedMessage.toCharArray();
					for (char characters:EncodedMessageArray) {
						enigma.encodeLetter(characters);
					}System.out.println("When in type " + type0 + " "+ type1 + " "+ type2);	
				}
			}
		}
		
		
		
		
		
		
	}
}
