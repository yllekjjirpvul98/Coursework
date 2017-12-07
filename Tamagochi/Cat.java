
public class Cat extends Animal{

	public Cat(String name, String gender, String breed, Integer age) throws InterruptedException {
		super(name, gender, breed, age);
	}
	
	public void Play() throws InterruptedException {
		Toolbox myToolbox = new Toolbox();
		System.out.println("Press 1 to play with balls");
		System.out.println("Press 2 to play with fish");
		System.out.println("Press 3 to play with mouse");
		System.out.println("Press 4 to play with cockroach");
		Integer option = myToolbox.readIntegerFromCmd();
		if (option.equals(1)) {
			System.out.println(name + "is rolling with the ball...");	
		}else if (option.equals(2)) {
			System.out.println(name + "is beating the fish....");
		}else if (option.equals(3)) {
			System.out.println(name + "is chasing after the mouse");
		}else if (option.equals(4)) {
			System.out.println(name + "is fiddling with a cockroach... Ew");
		}
		super.Play();
	}
	
	public void Eat() throws InterruptedException {
		Toolbox myToolbox = new Toolbox();
		System.out.println("Press 1 to feed fish");
		System.out.println("Press 2 to feed cat food");
		System.out.println("Press 3 to feed cheese");
		Integer option = myToolbox.readIntegerFromCmd();
		if (option.equals(1)) {
			System.out.println("The fish smells so good...");
		}else if (option.equals(2)) {
			System.out.println("The cat food is a bit dry but it's alright");
		}else if (option.equals(3)) {
			System.out.println("The cheese smells so good!");
		}
		super.Eat();
	}
}
