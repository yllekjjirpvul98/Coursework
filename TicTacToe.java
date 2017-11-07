import java.util.Scanner;
import java.util.Random;
public class TicTacToe {
	String Grid[][] = new String[3][3];
	String computersymbol;
	String playersymbol;
	int turn = 0;
	int row;
	int column;
	TicTacToe game;
	boolean win;
	boolean draw;
	boolean lose;
	public static void main(String[] args) {
			TicTacToe game = new TicTacToe();
			Toolbox myToolbox = new Toolbox();
			game.SetGrid();
			System.out.println("Welcome to the Tic Tac Toe Game!");
			System.out.println("Please select which symbol to use (O or X)");
			String symbol  = myToolbox.readStringFromCmd();
			if (symbol.equals("O")){
				game.setPlayerSymbol("O");
				game.setComputerSymbol("X");
			}else if (symbol.equals("X")){
				game.setComputerSymbol("O");
				game.setPlayerSymbol("X");
			}	
			System.out.println("Let's start the game!");
			while(!(game.getWin()) && !(game.getLose()) && !(game.getDraw())) {
				game.AskForUserChoice();
				game.PrintGrid();
				game.CheckDraw();
				game.WinOrLose();
				game.GameState();
				System.out.println("Computer's Turn!");
				game.ComputerProcess();
				game.PrintGrid();
				game.CheckDraw();
				game.WinOrLose();
				game.GameState();
			}			
	}
	public void setPlayerSymbol(String symbol){
		playersymbol = symbol;
	}
	public void setComputerSymbol(String symbol){
		computersymbol = symbol;
	}
	public boolean getWin() {
		return win;
	}
	public boolean getLose() {
		return lose;
	}
	public boolean getDraw(){
		return draw;
	}
	public void GameState() {
		String options;
		if (win) {
			System.out.println("Congratulations! You win the game!");
		}else if (lose) {
			System.out.println("You lose the game!");
		}else if (draw) {
			System.out.println("Oops! It's a draw!");
		}
		if (win||lose||draw){
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
	}


	public void CheckDraw(){
		for (int row=0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (Grid[row][column].equals(" ")){
					break;
				}else if (row == 2 && column == 2) {
					draw = true;
				}
			}
		}
	}
					

	public void WinOrLose(){
		if ((((Grid[0][0].equals(Grid[0][1]))&& (Grid[0][1].equals(Grid[0][2]))) && (Grid[0][2].equals(playersymbol)))||(((Grid[0][0].equals(Grid[1][0])) && (Grid[1][0] == Grid[2][0])) && (Grid[0][0].equals(playersymbol))) || (((Grid[0][0].equals(Grid[1][1])) && (Grid[1][1].equals(Grid[2][2]))) && (Grid[2][2].equals(playersymbol)))||(((Grid[1][0] == Grid[1][1]) && (Grid[1][1]== Grid[1][2])) && (Grid[1][0].equals(playersymbol))) || (((Grid[2][0]==Grid[2][1]) &&(Grid[2][1]== Grid[2][2])) && (Grid[0][0].equals(playersymbol))) ||(((Grid[2][0]==Grid[2][1]) &&(Grid[2][1] ==Grid[2][2])) && (Grid[2][0].equals(playersymbol)))||(((Grid[0][1] == Grid[1][1]) &&(Grid[1][1]== Grid[2][1])) && (Grid[0][1].equals(playersymbol)))||(((Grid[0][2] == Grid[1][2]) &&(Grid[1][2] == Grid[2][2])) && (Grid[0][2].equals(playersymbol))))
		{
				win = true;
			}else if ((((Grid[0][0] == Grid[0][1]) && (Grid[0][1] == Grid[0][2])) && (Grid[0][0].equals(computersymbol)))||(((Grid[0][0] == Grid[1][0]) && (Grid[1][0] == Grid[2][0])) && (Grid[0][0].equals(computersymbol))) || (((Grid[0][0] == Grid[1][1]) && (Grid[1][1] == Grid[2][2])) && (Grid[0][0].equals(computersymbol)))||(((Grid[1][0] == Grid[1][1]) && (Grid[1][1]) == Grid[1][2]) && (Grid[1][0].equals(computersymbol))) || (((Grid[2][0]==Grid[2][1]) &&(Grid[2][1] == Grid[2][2])) && (Grid[2][0].equals(computersymbol))) ||(((Grid[2][0]==Grid[2][1]) && (Grid[2][1]==Grid[2][2])) && (Grid[2][0].equals(computersymbol)))||(((Grid[0][1] == Grid[1][1]) && (Grid[1][1]== Grid[2][1])) && (Grid[0][1].equals(computersymbol)))||(((Grid[0][2] == Grid[1][2]) && (Grid[1][2] == Grid[2][2])) && (Grid[0][2].equals(computersymbol)))){
				lose = true;
			}
	}
	public void ComputerProcess(){
		if (turn == 1) {
			if (Grid[1][1].equals(" ")) {
				Grid[1][1] = computersymbol;
			}else{
			while (true) {
			Random rand = new Random();
			row  = rand.nextInt(2);
			column = rand.nextInt(2);
			 if (Grid[row][column].equals(" ")){					 
				Grid[row][column] = computersymbol;
				break;
			}
			}
		}
		}if (Grid[1][1].equals(playersymbol)){
			if ((Grid[0][0].equals(playersymbol)) && (Grid[2][2].equals(" "))) {
				Grid[2][2] = computersymbol;
			} else if (Grid[0][2].equals(playersymbol) && Grid[2][0].equals(" ")){
				Grid[2][0] = computersymbol;
			} else if (Grid[1][0].equals(playersymbol) && Grid[1][2] ==" "){
				Grid[1][2] = computersymbol;
			} else if (Grid[1][2].equals(playersymbol) && Grid[1][0].equals(" ")){
				Grid[1][0] = computersymbol;
			} else if (Grid[2][0].equals(playersymbol) && Grid[0][2].equals(" ")){
				Grid[0][2] = computersymbol;
			} else if (Grid[2][2].equals(playersymbol) && Grid[0][0].equals(" ")){
				Grid[0][0] = computersymbol;
			} else if (Grid[1][0].equals(playersymbol) && Grid[1][2].equals(" ")){
				Grid[1][2] = computersymbol;
			} else if (Grid[0][1].equals(playersymbol) && Grid[2][1].equals(" ")){
				Grid[2][1] = computersymbol;
			} else if (Grid[2][1].equals(playersymbol) && Grid[0][1].equals(" ")){
				Grid[0][1] = computersymbol;
			}
			}else if (((Grid[0][0].equals(playersymbol)) && (Grid[1][0].equals(playersymbol) || Grid[2][0].equals(playersymbol))) || ((Grid[1][0].equals(playersymbol)) && (Grid[0][0].equals(playersymbol) || Grid[2][0].equals(playersymbol)))||((Grid[2][0].equals(playersymbol)) && (Grid[0][0].equals(playersymbol) || Grid[1][0].equals(playersymbol)))){
			if (Grid[1][0].equals(" ")) {
				Grid[1][0] = computersymbol;
			} else if (Grid[0][0] ==" ") {
				Grid[0][0] = computersymbol;
			} else if (Grid[2][0].equals(" ")) {
				Grid[2][0] = computersymbol;
			}
			}else if (((Grid[0][0].equals(playersymbol)) && (Grid[0][1].equals(playersymbol) || Grid[0][2].equals(playersymbol))) || ((Grid[0][1].equals(playersymbol)) && (Grid[0][0].equals(playersymbol) || Grid[0][2].equals(playersymbol)))||((Grid[0][2].equals(playersymbol)) && (Grid[0][0].equals(playersymbol) || Grid[0][1].equals(playersymbol)))){
			if (Grid[0][0].equals(" ")) {
				Grid[0][0] = computersymbol;
			} else if (Grid[0][1] ==" ") {
				Grid[0][1] = computersymbol;
			} else if (Grid[0][2].equals(" ")) {
				Grid[0][2] = computersymbol;
			}
			}else if (((Grid[0][2].equals(playersymbol)) && (Grid[1][2].equals(playersymbol) || Grid[2][2].equals(playersymbol))) || ((Grid[1][2].equals(playersymbol)) && (Grid[0][2].equals(playersymbol) || Grid[2][2].equals(playersymbol)))||((Grid[2][2].equals(playersymbol)) && (Grid[0][2].equals(playersymbol) || Grid[1][2].equals(playersymbol)))){
			if (Grid[1][2].equals(" ")) {
				Grid[1][2] = computersymbol;
			} else if (Grid[2][2] ==" ") {
				Grid[2][2] = computersymbol;
			} else if (Grid[0][2].equals(" ")) {
				Grid[0][2] = computersymbol;
			}
			}else if (((Grid[2][0].equals(playersymbol)) && (Grid[2][1].equals(playersymbol) || Grid[2][2].equals(playersymbol))) || ((Grid[2][1].equals(playersymbol)) && (Grid[2][0].equals(playersymbol) || Grid[2][2].equals(playersymbol)))||((Grid[2][2].equals(playersymbol)) && (Grid[2][0].equals(playersymbol) || Grid[2][1].equals(playersymbol)))){
			if (Grid[2][0].equals(" ")) {
				Grid[2][0] = computersymbol;
			} else if (Grid[2][1] ==" ") {
				Grid[2][1] = computersymbol;
			} else if (Grid[2][2].equals(" ")) {
				Grid[2][2] = computersymbol;
			}
			}else{			
			while (true) {
			Random rand = new Random();
			row  = rand.nextInt(2);
			column = rand.nextInt(2);
			 if (Grid[row][column].equals(" ")){					 
				Grid[row][column] = computersymbol;
				break;
			 }
			}
			}
	}

	public void AskForUserChoice(){
		Scanner scanner = new Scanner(System.in);
		while (true) {
		System.out.println("Please input the row number (0-2)");
		row = Integer.parseInt(scanner.next());
		System.out.println("Please input the column (0-2)");
		column = Integer.parseInt(scanner.next());
		if (Grid[column][row].equals(" ")){
			Grid[column][row] = playersymbol;
			turn += 1;
			break;
		}else {
			System.out.println("This space is occupied!");
		}
		}
	}
	public void SetGrid() {
		for (int column = 0; column < 3; column++) {
			for (int row=0; row < 3; row++) {
				Grid [row][column] = " ";
			}
		}
	}
	public void PrintGrid() { //method to print grid
		for (int column = 0; column < 3; column++) {
			for (int row=0; row < 3; row++)  {
			System.out.print("| "+Grid[row][column]+" |");
			}
		System.out.println();
		System.out.println("---------------");
		}
	}

}