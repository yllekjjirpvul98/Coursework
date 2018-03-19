import java.io.IOException;

/* Extension:
 * This class will deal with input file and convert the input into encode-able text
 * including removing white space, combining two lines into one line and remove punctuation
 */

public class ConvertFile extends EnigmaFile{
	
	//constructor that take in the read path and the write path
	public ConvertFile(String read_path, String write_path) {
		super(read_path, write_path);
	}
	
	//method that remove unwanted features such as space or special symbols
	public String RemoveUnwantedFeatures(String[] text) throws IOException {
		int NumOfLines = this.NumOfLines();
		String TextToEncode = "";
		for (int i=0; i < NumOfLines; i++) {
			for (char character:text[i].toCharArray()) {
				if (Character.isLetter(character)) {
					TextToEncode += character;
				}
			}
		}return TextToEncode;
	}
	
	//method that convert all lowercase latter to upper case
	public String ChangeToCapital(String text) throws IOException {
		String TextToEncode = text.toUpperCase();
		return TextToEncode;
	}
	
	
	public static void main(String[] args) throws IOException {
	
		try {
			ConvertFile file = new ConvertFile("read_extension.txt", "write.txt");
			String[] text = file.ReadFile();
			file.setUpEngimaMachine();
			String EncodeableText = file.RemoveUnwantedFeatures(text);
			EncodeableText = file.ChangeToCapital(EncodeableText);
			char[] CharArray = file.ConvertToCharArray(EncodeableText);
			file.encodeFile(CharArray);
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
	}
		
	}
}
	
