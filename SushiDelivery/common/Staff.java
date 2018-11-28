/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import server.Server;

public class Staff extends Model implements Runnable{
	Dish d;
	stockManager manager = stockManager.getManager();
	Status status = Status.IDLE;
	Status originalStatus;
	transient Object pauseLock = new Object();
	private boolean running = true;
	public static ArrayList<Staff> staffList = new ArrayList<Staff>();
	public transient Object actionLock = Drone.actionLock;
	
	enum Status {
		IDLE, PREPARING, PAUSE
	}
	
	public Staff(String name){
		setName(name);
		staffList.add(this);
	}
	
	public static ArrayList<Staff> getStaffList() {
		return staffList;
	}
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	//method to check whether there is sufficient ingredient to create the dish
	public boolean checkSufficientIngredients(Dish d) {
			boolean status = false;
			HashMap<Ingredient, Number> ingredients = d.getIngredients();
			Map<Ingredient, Number> ingredientStock = stockManager.getIngredientStock();
			for (Ingredient i : ingredients.keySet()) {
				for (Ingredient ingredient : ingredientStock.keySet()) {
					if (i.toString().equals(ingredient.toString())) {
						synchronized(i) {
							synchronized(ingredient) {
								if ((Integer)ingredientStock.get(ingredient) >= (Integer)ingredients.get(i)) {
									status = true;
								}else {
									status = false;
									break;
								}
							}
						}
					}else continue;
				}if (!status) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}return status;
	}
	
	//check whether the dish needs restocking
	public boolean checkStock(Dish d) {
		boolean status = false;
		synchronized(d) {
			Set<Dish> dishList = stockManager.getDishStock().keySet();
			for (Dish dish : dishList) {
				if (dish.toString().equals(d.toString())) {
					status = stockManager.getManager().checkRestock(dish);
					break;
				}
			}
		}return status;
	}
	
	//method for the staff to create new dishes
	public void prepareNewDish() {
			Integer number = null;
			HashMap<Ingredient, Number> ingredients;
			Set<Dish> dishList = stockManager.getDishStock().keySet();
			for (Dish d : dishList) {
				synchronized(d) {
				if (!Thread.holdsLock(d)) {
					continue;
				}else {
					if (checkStock(d)) {
						if (checkSufficientIngredients(d)) {
							System.out.println(name + " thread running");
							status = Status.PREPARING;
							System.out.println(Server.getServer() == null);
							Server.getServer().notifyUpdate();
							int t = (int) (Math.random() * (20000 - 50000)) + 50000;
							try {
								System.out.println(name + " is restocking");
								Thread.sleep(t);
								for (Dish dish : stockManager.getDishStock().keySet()) {
									if (dish.toString().equals(d.toString())) {
										number = (Integer)d.getRestocking() + (int)dish.getThreshold() - (int)stockManager.getDishStock().get(dish);
										stockManager.getManager().addStock(d, number);
										break;
									}
								}
								Server.getServer().notifyUpdate();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ingredients = d.getIngredients();
							for (Ingredient i : ingredients.keySet()) {
								synchronized(i) {
									Integer amount = (Integer)ingredients.get(i) * number;
									for (Ingredient ingredient : stockManager.getIngredientStock().keySet()) {
										if (ingredient.toString().equals(i.toString())) {
											stockManager.getManager().addStock(ingredient, -amount);
										}
									}
									
								}
							}break;
					}else continue;
			}
			}
			}
			}status = status.IDLE;
			Server.getServer().notifyUpdate();
					
}


	public String getStatus() {
		return status.name();
	}
	
	public void pause() {
		originalStatus = status;
		status = Status.PAUSE;
	}
	
	public void resume() {
		synchronized(pauseLock) {
			status = originalStatus;
			pauseLock.notifyAll();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running) {
			synchronized(pauseLock) {
				if (status.equals(Status.PAUSE)) {
					try {
						pauseLock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						break;
					}
				}else if (status.equals(Status.IDLE)) {
						prepareNewDish();

				}
			}
		}
		
	}

}
