public class Dog extends Animal{
	
	public Dog(String name, String gender, String breed, Integer age) throws InterruptedException {
		super(name, gender, breed, age);
	}
	
	public void Play() throws InterruptedException {
		Toolbox myToolbox = new Toolbox();
		System.out.println("Press 1 to play with balls");
		System.out.println("Press 2 to play with tissue");
		System.out.println("Press 3 to play with sofa");
		System.out.println("Press 4 to play with cockcroach");
		Integer option = myToolbox.readIntegerFromCmd();
		if (option.equals(1)) {
			System.out.println(name + "is rolling with the ball...");	
		}else if (option.equals(2)) {
			System.out.println(name + "is chewing the tissue....");
		}else if (option.equals(3)) {
			System.out.println(name + "is scratching the sofa...");
		}else if (option.equals(4)) {
			System.out.println(name + "is fiddling with a cockroach... Ew");
		}
		super.Play();
	}
	
	public void Eat() throws InterruptedException {
		Toolbox myToolbox = new Toolbox();
		System.out.println("Press 1 to feed bone");
		System.out.println("Press 2 to feed dog food");
		System.out.println("Press 3 to feed snacks");
		Integer option = myToolbox.readIntegerFromCmd();
		if (option.equals(1)) {
			System.out.println("The bone sores its tooth...");
		}else if (option.equals(2)) {
			System.out.println("The dog food is a bit dry but it's alright");
		}else if (option.equals(3)) {
			System.out.println("Yumyum!");
		}
		super.Eat();
	}
}
