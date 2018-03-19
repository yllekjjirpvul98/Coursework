public class Wolf extends Carnivore {
	/*wolf inherits from carnivore */
	
	public Wolf(String name, Integer age) {
		/* constructor that defines the name and age */
		super(name,age);
	}
	
	/*method to make noise */
	public void makeNoise() {	
		System.out.println("Woof");
	}
}
