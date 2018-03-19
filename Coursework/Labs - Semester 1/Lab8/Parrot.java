/* parrot inherits from omnivore */
public class Parrot extends Omnivore {
	
	/*constructor that defines the name and the age */
	public Parrot(String name, Integer age) {
		super(name,age);
	}
	
	public Parrot(Integer age) {
		super(age);
		this.name = "Polly";
	}
	
	/* method to make noise */
	public void makeNoise() {
		System.out.println("Squawk");
	}
	
}
