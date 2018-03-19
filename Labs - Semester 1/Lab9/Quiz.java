import java.util.ArrayList;
import java.util.ListIterator;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Quiz {
	FlashCardReader fcr = new FlashCardReader();
	ArrayList<FlashCard> flashCardArray;
	ArrayList<String> questionArray;
	ArrayList<String> answerArray;
	ArrayList<String> statusArray;
	int num_of_question = 0;
	int correctAns = 0;
	
	public Quiz() throws FileNotFoundException {
		//store flashCardArray fetched from flash card reader to flashCardArray
		flashCardArray = fcr.getFlashCard();
		this.play();
	}
	
	private void play() throws FileNotFoundException {
		//create toolbox object so that can use its method later
		Toolbox myToolbox = new Toolbox(); 
		questionArray = new ArrayList<String>();
		answerArray = new ArrayList<String>();
		statusArray = new ArrayList<String>();
		
		//loop through the flashcard array
		for (int i=0; i < flashCardArray.size(); i++) {
			String question = flashCardArray.get(i).getQuestion(); //print out the question
			System.out.println(question);
			//store question to the question array
			questionArray.add(i, question);
			String playerAnswer = myToolbox.readStringFromCmd();//read string from cmd line
			//store player's answer to the answer array
			answerArray.add(i, playerAnswer);
			num_of_question += 1;
			
			//if player answer equals to answer stored in the flashcard
			if (playerAnswer.equals(flashCardArray.get(i).getAnswer())) { 
				System.out.println("Your answer is right!");
				correctAns += 1;
				//store correct to the status array if the question is answered correctly
				statusArray.add(i, "correct");
			}else {
				System.out.println("Oops! Your answer is wrong!");
				System.out.println("The correct answer should be " + flashCardArray.get(i).getAnswer()+"!");
				//store wrong to the status if wrongly answered
				statusArray.add(i, "wrong");
			}
		}
		System.out.println("Do you want to save your result? Type Y or N");
		String response = myToolbox.readStringFromCmd();
		
		//if player want to save then run the save method
		if (response.equals("Y")){
			save();
		}
	}
	
	//save method that creates a new printstream into the save.txt file
	private void save() throws FileNotFoundException {
		int percentage = 0;
		PrintStream ps = new PrintStream("src/save.txt");
		
		for (int i=0; i<num_of_question; i++) {
			ps.println(questionArray.get(i)+" ," + answerArray.get(i)+" ," + statusArray.get(i));
		}
		try {
			//formula to calculate the percentage of the test
			percentage = (correctAns * 100)/num_of_question;
			ps.println("Number of questions asked: " + num_of_question);
			ps.println("Score: " + correctAns + "/" + num_of_question);
			ps.println("Percentage: " + percentage +"%");
		}catch (ArithmeticException e) {
			System.out.println("You haven't answered any question!");
		}
	}
	
	public static void main(String[] args)  {
	try {
		Quiz quiz = new Quiz();
	}catch(FileNotFoundException e) {
		System.out.println("File is not found");
	}
}
