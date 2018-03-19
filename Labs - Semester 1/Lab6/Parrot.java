public class Parrot extends Omnivore {
	/* parrot inherits from omnivore */
	
	public Parrot(String name, Integer age) {
		/*constructor that defines the name and the age */
		super(name,age);
	}
	
	public void makeNoise() {
		/* method to make noise */
		System.out.println("Squawk");
	}
	
}
