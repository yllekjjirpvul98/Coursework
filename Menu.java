public class Menu {
	Animal pet;
	Toolbox myToolbox = new Toolbox();
	
	public Menu() {
		try {
		setUpNewAnimal();
		while(true) {
			pet.printStatus();
			pet.CheckHappiness();
			pet.CheckHP();
			askAction();
			
		}}catch(InterruptedException e) {
			
			System.out.println("Sorry! Something wrong with your input");
		}
	}
	
	public void setUpNewAnimal() throws InterruptedException {
		System.out.println("Welcome to the Tamogochi game!");
		System.out.println("What type of animal do you want? Answer Cat or Dog.");
		String type = myToolbox.readStringFromCmd();
		System.out.println("Give it a name!");
		String name = myToolbox.readStringFromCmd();
		System.out.println("Do you want it to be a boy or a girl? Answer Male or Female");
		String gender = myToolbox.readStringFromCmd();
		System.out.println("What breed do you want it to be?");
		String breed = myToolbox.readStringFromCmd();
		if (type.equals("Cat")) {
			pet = new Cat(name, gender, breed, 0);
		}else if (type.equals("Dog")) {
			pet = new Dog(name, gender, breed, 0);
		}
	}
	
	public void askAction() throws InterruptedException {
		System.out.println("What do you want your pet to do?");
		System.out.println("Press 1 if you want it to eat");
		System.out.println("Press 2 if you want it to play");
		System.out.println("Press 3 if you want it to sleep");
		System.out.println("Press 4 if you want to quit this game");
		Toolbox myToolbox = new Toolbox();
		int option = myToolbox.readIntegerFromCmd();
		if (option == 1) {
			pet.Eat();
		}else if (option ==2) {
			pet.Play();
		}else if (option ==4) {
			System.exit(0);
		}else if (option==3) {
			pet.Sleep();
		}
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu();
	}
}
	
	
	
	
	
