import java.util.ArrayList;

public class Main {
	
	public static void main(String[] arg) throws Exception {
		
		/*creating instances of animals and food */
		Animal wolf = new Wolf("Lupus", 5);
		Animal parrot = new Parrot("Kiwi", 15);
		Animal giraffe = new Giraffe("Maggie", 19);
		Food pork = new Meat("pork");
		Food vege = new Plant("vegetables");
		
		/* testing the makenoise method which demonstrate polymorphism*/
		ArrayList<Animal> animal = new ArrayList<Animal>();
		animal.add(wolf);
		animal.add(parrot);
		for (Animal myanimal:animal) {
			myanimal.makeNoise();
		}	
/*		
		ArrayList<Animal> animal2 = new ArrayList<Animal>();
		animal2.add(wolf);
		animal2.add(parrot);
		animal2.add(giraffe);
		for (Animal myanimal:animal2) {
			myanimal.eat(new Plant("banana"));
		}
*/		
		try { 
		/* Feed my herbivore with meat */
		Food chicken = new Meat("chicken");
		giraffe.Feed(chicken);
		
		/*Feed my carnivore with plant */
		Food carrot = new Plant("carrot");
		wolf.Feed(carrot);
		
		/* catch the exception and print out the exception message*/
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		

		 //testing overloading eat()
		 //Result: the text is printed five times
		Food food = new Meat("chicken");
		wolf.Feed(food, 5);
		
		//testing overloading in constructors
		Wolf wolf1 = new Wolf();
		System.out.println(wolf1.getAge());
		System.out.println(wolf1.getName());
		/* Output is 0 and newborn */
		
	}
	
	/* Result is that only message for herbivore is print. because once the exception is 
	 * catched and the error message is print, it breaks out from the try loop and hence
	 * the code feeding the carnivore is not executed 
	 */
	
}
