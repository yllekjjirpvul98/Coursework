import java.util.Scanner; 
import java.util.Random;
public class RPSGame{
    int win = 0;
    int turn = 0;
    int lose = 0;
    public int GetUserInput(){
        System.out.println("Press 1 to choose rock; Press 2 to choose paper; Press 3 to choose scissor");
        Scanner scan = new Scanner(System.in);
        int userchoice = scan.nextInt();
        return userchoice;
    }
    public int ComputerChoice(){
       Random rand = new Random();
       int compchoice = rand.nextInt(3)+1;
       return compchoice;
    }
    public String DigitToChoice(int choice){
       String result = " ";
       if (choice == 1) {
           result = "Rock";
        }else if (choice ==2) {
            result =  "Paper";
        }else if (choice ==3) {
            result =  "Scissor";
        }
	return result;
    }
    public void Results(String user, String comp) {
        turn += 1;
        if (user == comp) {
            System.out.println ("Draw!");
	    turn -= 1;
        }else if (user == "Rock" && comp == "Scissor" || user == "Paper" && comp == "Rock"||user == "Scissor" && comp == "Paper"){
            System.out.println ("You win one turn!");
	    win += 1;
        }else{
            System.out.println("You lose one turn!");
	    lose +=1;
        }
    }   
    public void checkWin() {
	if (turn == 2 && win ==2 || turn == 3 && win==2) {
		System.out.println("Congratulations! You win the game!");
		System.exit(0);
	}else if(turn == 2&& lose==2 || turn==3 && lose==2) {
		System.out.println("Oops! You lose the game :(");
		System.exit(0);
	}
    }
	
		
    public static void main(String[] args) {
        System.out.println("Welcome to the rock, paper and scissor game!");
        RPSGame game = new RPSGame();
	while (true) {
        	String user_choice = game.DigitToChoice(game.GetUserInput());
       		String comp_choice = game.DigitToChoice(game.ComputerChoice());
		System.out.println("You choose a " + user_choice +"!");
        	System.out.println("The computer choose a " + comp_choice+"!");
        	game.Results(user_choice, comp_choice);
		game.checkWin();
	}
    }
}
              
