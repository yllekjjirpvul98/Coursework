import java.util.ArrayList;
import java.util.Collections;

public class Demo {
	public static void main(String[] args) {
		ArrayList<Animal> AnimalArray = new ArrayList<Animal>();
		AnimalArray.add(new Parrot("Spooky", 20));
		AnimalArray.add(new Wolf());
		AnimalArray.add(new Giraffe("Goofy", 18));
		
		
		/* Interface is like abstract class and a contract that promises to implement the method
		 * It is different from abstract class because extend means the subclass must implement
		 * all the methods inside the abstract class but interface simply means that the class is
		 * free to implement those methods however it sees fit
		 */
		
		System.out.println("Animal order before sorting:");
		for (Animal animal:AnimalArray) {
			System.out.println(animal.getName() + ", " + animal.getAge());
		}
		
		//this line will sort the array according to the value compareTo returns
		Collections.sort(AnimalArray);
		Collections.reverseOrder();
		
		System.out.println("Animal order after sorting:");
		for (Animal animal:AnimalArray) {
			System.out.println(animal.getName() + ", " + animal.getAge());
		}
		
		/* We can make the animals be ordered from highest age to lowest age by setting it to negative
		 * number if current object greater than given object and positive when current object smaller 
		 * than given object 
		 */
		
	}
}
