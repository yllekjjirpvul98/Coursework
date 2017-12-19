public abstract class Animal{
	String name;
	String gender;
	String breed;
	Integer age;
	Boolean sleepy = false;
	Integer HP = 100;
	Integer happiness;
	Toolbox myToolbox = new Toolbox();
	
	public Animal(String name, String gender, String breed, Integer age) throws InterruptedException {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.breed = breed;
		setHappiness (100);
		setHP(100);
	}
	
	public void setHappiness(Integer h) {
		happiness = h;
	}
	

	public void setHP(int HP) {
		this.HP = HP;
	}
	public void printStatus() {
		String name = ("Name: " + this.name);
		String gender = ("Gender: " +this.gender);
		String breed = ("Breed: " + this.breed);
		String age = ("Age: " + this.age);
		String happiness = ("Happiness: " + this.happiness+"/100");
		String HP = ("HP: " + this.HP + "/100");

		System.out.println(name);
		System.out.println(gender);
		System.out.println(breed);
		System.out.println(age);
		System.out.println(happiness);
		System.out.println(HP);
	}
	
	public void AddHP(Integer HP) {
			int newHP = this.HP + HP;
			if (newHP >= 100) {
				newHP = 100;
			}else if (newHP <= 0) {
				newHP = 0;
			}
			setHP(newHP);
		}
	
	public void AddHappiness(Integer happiness) {
		int newHappiness = this.happiness + happiness;
		if (newHappiness >= 100) {
			newHappiness = 100;
		}else if (newHappiness <=0) {
			newHappiness =0;
		}
		setHappiness(newHappiness);
	}
	
	public void CheckHappiness () throws InterruptedException {

			if (happiness.equals(100)) {
				System.out.println("You have reach a maximum happiness!");
				Thread.sleep(1000);
				System.out.println("Good job! " + name + " is super duper happy!");
				Thread.sleep(1000);
			}else if (happiness.equals(0))  {
				System.out.println("Happiness reaches 0");
				Thread.sleep(1000);
				Integer prob = myToolbox.getRandomInteger(10000);
				if (prob < 100) {
					/* 1% percentage that pet will leaves home */
					System.out.println(name + "decides to leave home");
					Thread.sleep(1000);
					System.out.println("You have lost a pet!");
					Thread.sleep(1000);
				} else {
				System.out.println(name + " is really lonely...");
				Thread.sleep(1000);
				System.out.println("Try to cheer her up!");
				Thread.sleep(1000);
				}
			}
		
	}
	
	public void CheckHP() throws InterruptedException {
		
			if(HP.equals(0)) {
				Integer prob = myToolbox.getRandomInteger(10000);
				if (prob < 100) { 
				/* 1% probability that pet will pass away */
					System.out.println(name + "is too tired...");
					Thread.sleep(1000);
					System.out.println(name + "falls asleep");
					Thread.sleep(1000);
					System.out.println("and never open its eyes again...");
					Thread.sleep(1000);
					System.out.println("Unfortunately, " + name + " has left us forever");
				} else {
					System.out.println(name + "is too tired so it automatically falls asleep");
					this.Sleep();
				}
			}
		
	}
	
	public void Sleep() throws InterruptedException {
		System.out.println("Sleeping...");
		Thread.sleep(1000);
		System.out.println(name + "'s HP is fully recovered");
		this.AddHP(100);
		sleepy = !sleepy;
	}
	
	public void Eat() throws InterruptedException {
		System.out.println("Munching...");
		Thread.sleep(1000);
		Integer prob = myToolbox.getRandomInteger(10000);
		if (prob < 3333) {
			/* Event 1 - Bad food */
			System.out.println("Seems that the meat is a bit raw...");
			Thread.sleep(1000);
			System.out.println(name + " suffers from a diarrhea...");
			this.AddHP(-50);
			Thread.sleep(1000);
			System.out.println(name + "'s HP decreased by 50");
			Thread.sleep(1000);
			System.out.println(name + "'s happiness decreased by 50");
			Thread.sleep(1000);
			this.AddHappiness(-50);
		}else if (prob > 3333 && prob< 6666) {
			/* Event 2 - Sleepy */
			System.out.println(name + " is very satisfied with its food");
			Thread.sleep(1000);
			System.out.println("Time for a sleep after a good meal");
			Thread.sleep(1000);
			System.out.println(name + " 's eyelid becomes really heavy...");
			Thread.sleep(1000);
			System.out.println(name + "Zzz...");
			Thread.sleep(1000);
			this.Sleep();
		}else if (prob > 6666) {
			/* Event 3 - Nice meal*/
			System.out.println(this.name + " has a good meal");
			Thread.sleep(1000);
			System.out.println(name + "'s HP is recovered by 10");
			this.AddHP(10);
		}
	}
	
	public void Play() throws InterruptedException {
		Integer prob = myToolbox.getRandomInteger(10000);
		if (prob < 3333) {
			/* Event 1 - normal */
			System.out.println(name + " is so happy!");
			Thread.sleep(1000);
			System.out.println("Happiness increases by 50");
			Thread.sleep(1000);
			System.out.println("HP decreases by 50");
			Thread.sleep(1000);
			this.AddHP(-50);
			this.AddHappiness(50);
		}else if (prob > 3333 && prob < 6666) {
			/* Event 2 - get hurts */
			System.out.println("Ouch!");
			Thread.sleep(1000);
			System.out.println(name + " has hurted itself...");
			Thread.sleep(1000);
			System.out.println("HP decreased by 70");
			Thread.sleep(1000);
			System.out.println("Happiness decreased by 30");
			Thread.sleep(1000);
			this.AddHP(-70);
			this.AddHappiness(-30);
		} else if (prob > 6666) {
			/* Event 3 - eat something bad */
			System.out.println(name + " sees something shining on the ground");
			Thread.sleep(1000);
			System.out.println(name + " swallow it!");
			Thread.sleep(1000);
			System.out.println(name + "'s stomach is churning...");
			Thread.sleep(1000);
			System.out.println("but " + name + " is really proud of itself!");
			Thread.sleep(1000);
			System.out.println("HP decreases by 50");
			Thread.sleep(1000);
			System.out.println("Happiness increases by 20");
			Thread.sleep(1000);
			this.AddHappiness(20);
			this.AddHP(-50);
		}
	}
}
