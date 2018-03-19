public class Carnivore extends Animal {
	/*carnivore inherits from the animal class */

	public Carnivore(String name, Integer age) {
		/* constructor that defines the name and the age */
		super(name, age);
	}
	
	//overload the constructor
	public Carnivore() {
		super();
	}
	
	public void eat(Food food) throws Exception {
		if (!(food instanceof Meat)) {
			/* if food is not type of meat 
			 * throw exception message*/
			throw new Exception("Carnivore are not vegetarians!");
		}else {
			System.out.println("Chewing the " + food.getName() + "...");
		}
	}
	
	
}
