public class Omnivore extends Animal{
	/*omnivore inherits from animal */

	public Omnivore(String name, Integer age) {
		/* constructor that takes in the name and the age */
		super(name, age);
	}
	
	public void eat(Food food) {
		/*method to eat the food */
		System.out.println("Eating the " + food.getName() +("..."));
	}

}
