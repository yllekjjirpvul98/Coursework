//Reference: http://www.homeandlearn.co.uk/java/read_a_textfile_in_java.html

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileWriter;

public class EnigmaFile {

	protected String read_path;
	protected String write_path;
	
	//constructor that take in the read path and the write path
	public EnigmaFile(String read_path, String write_path) {
		this.read_path = read_path;
		this.write_path = write_path;
	}
	
	//method that calculate the number of lines in the file
	public int NumOfLines() throws IOException {
		
		FileReader fr = new FileReader(read_path);
		BufferedReader reader = new BufferedReader(fr);
		int numberOfLines = 0;
		
		//if readLine is not null then numberoflines add 1
		while (reader.readLine() != null) {
			numberOfLines++;	
		}
		reader.close();
		return numberOfLines;
	}
	
	public String[] text;
	//method that read the file by storing each line to a string array
	public String[] ReadFile() throws IOException {
		FileReader fr = new FileReader(read_path);
		BufferedReader reader = new BufferedReader(fr);
		
		int numOfLines = this.NumOfLines();
		text = new String[numOfLines];
		for (int i=0; i < numOfLines; i++) {
			text[i] = reader.readLine();
		}
		reader.close();
		return text;
	}
	
	//method that write the character to the file
	public void writeToFile(char letter) throws IOException{
		FileWriter writer = new FileWriter(write_path, true);
		PrintWriter print_line = new PrintWriter(writer);
		print_line.printf( "%s" , letter);
		print_line.close();
	}
	
	//method that write string to the file 
	//overloading but taking in a parameter of different type
	public void writeToFile(String string) throws IOException{
		FileWriter writer = new FileWriter(write_path, true);
		PrintWriter print_line = new PrintWriter(writer);
		print_line.printf( "%s" , string);
		print_line.close();
	}
	
	//method that split the string to a character array
	public char[] ConvertToCharArray(String text) throws IOException {
		char[] CharArray = text.toCharArray();
		return CharArray;
	}
	
	
	EnigmaMachine enigma;
	//method that set up the enigma machine 
	public void setUpEngimaMachine() {
		enigma = new EnigmaMachine();
		//for enigma file class we will be using read.txt
		if (read_path.equals("read.txt")) {
			enigma.clearPlugboard();
			enigma.addPlug('A', 'M');
			enigma.addPlug('G', 'L');
			enigma.addPlug('E', 'T');
			enigma.addRotor(new BasicRotor("I"), 0);
			enigma.addRotor(new BasicRotor("II"), 1);
			enigma.addRotor(new BasicRotor("III"), 2);
			enigma.setPosition(0, 6);
			enigma.setPosition(1, 12);
			enigma.setPosition(2, 5);
			enigma.addReflector(new Reflector("reflectorI"));
		//for convert file class we will be using read_extension.txt and they use different settings
		} else if (read_path.equals("read_extension.txt")) {
			enigma.clearPlugboard();
			enigma.addPlug('Q', 'F');
			enigma.addRotor(new TurnoverRotor("I"), 0);
			enigma.addRotor(new TurnoverRotor("II"), 1);
			enigma.addRotor(new TurnoverRotor("III"), 2);
			enigma.setPosition(0, 23);
			enigma.setPosition(1, 11);
			enigma.setPosition(2, 7);
			enigma.addReflector(new Reflector("reflectorI"));
		}
	}
	
	//method that encode the file and write the encoded letter to write.txt
	public void encodeFile(char[] CharArray) throws IOException {
		for (char character:CharArray) {	
			char encodedLetter = enigma.encodeLetter(character);
			this.writeToFile(encodedLetter);
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		try {
			EnigmaFile file = new EnigmaFile("read.txt", "write.txt");
			String[] textLine = file.ReadFile();
			file.setUpEngimaMachine();
			
			for (String textline:textLine) {
				char[] CharArray = file.ConvertToCharArray(textline);
				file.encodeFile(CharArray);
			}
		
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
