public abstract class Animal {
	String name;
	Integer age;
	
	public Animal(String name, Integer age) {
	/* constructor that defines the name and the age*/
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
	/*getter to get name */
		return name;
	}
	
	public Integer getAge() {
	/*getter to get age */
		return age;
	}	
	
	public void makeNoise() {
	/* abstract method */
	}
	
	public void eat(Food food) throws Exception{
		/*abstract method */
		
	}
	
	public void Feed(Food food) throws Exception {
		/*method built to feed the animal */
		this.eat(food);
	}
	

}
