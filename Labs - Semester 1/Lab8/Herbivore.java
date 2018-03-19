/* herbivore inherits from Animal */
public class Herbivore extends Animal {

	public Herbivore(String name, Integer age) {
		super(name, age);
	}
	
	public void eat(Food food) throws Exception {
		if (!(food instanceof Plant)) {
			/* if food is not a plant 
			 * throw exception */
			throw new Exception("Herbivore are vegetarians! Don't force them to eat meat!");
		}else {
		System.out.println("Swallowing the "+ food.getName() +"...Yum!");
		}
	}
}
