import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FlashCardReader {
	BufferedReader reader;
	ArrayList<FlashCard> flashCardArray = new ArrayList<FlashCard>();
	
	public FlashCardReader(){
		FileReader fr;
		try {
			fr = new FileReader("src/Question.txt"); //file reader read in the file question.txt
			reader = new BufferedReader(fr); //bufferedreader read in the content of question.txt
		} catch (FileNotFoundException e) {//catch exception
			System.out.println("Sorry! File is not found!");
		}
	}
	
	//this method return lines of the file as an arraylist of string
	private ArrayList<String> getLine() throws IOException {
		ArrayList<String> textLines = new ArrayList<String>();
		String thisLine;
		while ((thisLine = reader.readLine()) != null) {
			textLines.add(thisLine);
		}
		return textLines;
	}
	
	//check whether the file is ready
	private boolean fileReady() throws IOException {
		return reader.ready();
	}
	
	//return an arraylist of flashcards
	public ArrayList<FlashCard> getFlashCard(){
		try {
		for (String textLine:getLine()) {
				String[] QuesAndAns = textLine.split(":"); //chop the textline upon ":" and store it in an array of string
				FlashCard card = new FlashCard(QuesAndAns[0], QuesAndAns[1]);  //create flashcard with question and answer pair
				flashCardArray.add(card);//add the flashcard to the flashcard array
		}
		} catch(IOException e) {
			System.out.println("The file is empty");
		}return flashCardArray;
	}

}
