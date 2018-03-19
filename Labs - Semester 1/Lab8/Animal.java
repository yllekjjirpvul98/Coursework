public abstract class Animal implements Comparable<Animal>{
	String name;
	Integer age;
	
	/* constructor that defines the name and the age*/
	public Animal(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	
	public Animal(Integer age) {
		this.age = age;
	}
	
	public Animal() {
		this("newborn", 0);
	}
	
	/*getter to get name */
	public String getName() {
		return name;
	}
	
	/*getter to get age */
	public Integer getAge() {
		return age;
	}	
	
	/* abstract method */
	public void makeNoise() {
		
	}
	
	/*abstract method */
	public void eat(Food food) throws Exception{
		
	}
	
	//overloading the first eat method
	public void eat(Food food, Integer integer) throws Exception {
		for (int i = 0; i < integer; i++) {
			this.Feed(food);
		}
	}
	
	/*method built to feed the animal */
	public void Feed(Food food) throws Exception {
		this.eat(food);
	}
	
	/*overloading the first Feed method */
	public void Feed(Food food, Integer integer) throws Exception {
		this.eat(food, integer);
	}
	
	//interface method that compares an animal with other animals
	public int compareTo(Animal other) {
		int otherAge = other.getAge();
		int value = 0;
		//age - otherage
		if (this.age == otherAge) {
			//return 0 if they have the same age
			value = 0;
		}else if (this.age < otherAge) {
			//return -1 if the animal's age is smaller than the other animal
			value = -1;
		}else if (this.age > otherAge) {
			//return 1 if the animal's age is greater than the other animal
			value = 1;
		}return value;
	}

}
