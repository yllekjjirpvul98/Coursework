/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import server.Configuration;
import server.Server;

public class Drone extends Model implements Runnable, Serializable {
	boolean running = true;
	String name = "Drone";
	stockManager manager = stockManager.getManager();
	ArrayList<Order> orderList = Order.getOrderList();
	Integer speed;
	Ingredient i = null;
	Order o = null;
	Status status = Status.IDLE;
	transient Object pauseLock = new Object();
	public static ArrayList<Drone> droneList = new ArrayList<Drone>();
	public transient static Object actionLock = new Object();
	
	public Drone(Integer speed){
		this.speed = speed;
		droneList.add(this);
	}
	
	enum Status {
		DELIVERING, RESTOCKING, IDLE, PAUSE;
	}
	
	public static ArrayList<Drone> getDroneList() {
		return droneList;
	}
	
	public Number getSpeed() {
		return speed;
	}
	
	/* check whether the ingredient needs restocking by invoking
	 * the stock manager
	 */
	
	public boolean checkStock(Ingredient i) {
		boolean status = false;
		synchronized(i) {
			status = manager.checkRestock(i);
		}return status;
	}
	
	public void restock()  {
		Integer number = 0;
		/* loop through the ingredient stock and check whether the ingredient
		 * require restocking
		 */
		Set<Ingredient> ingredientList = stockManager.getIngredientStock().keySet();
		for (Ingredient i : ingredientList) {
			synchronized(i) {
					if (checkStock(i)) {
						status = Status.RESTOCKING;
						Server.getServer().notifyUpdate();
						Supplier s = i.getSupplier();
						Double time = (double)s.getDistance()/speed;
						try {
							System.out.println("Drone " + speed + " is restocking " + i.getName());
							Thread.sleep((long) (time * 10000));
							stockManager.getManager().addStock(i, i.threshold - (int)stockManager.getManager().getIngredientStock().get(i) + i.restocking);
							Server.getServer().notifyUpdate();
							break;
						}
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							
						}
					}else continue;
				}
			}status = Status.IDLE;
			
	}
	
	/* Check whether there is sufficient dish stock to complete the
	 * order
	 */
	
	public boolean checkSufficientDishStock(Order o) {
		boolean status = false;
		Set<Dish> dishList = stockManager.getManager().getDishStock().keySet();
		for (Dish d : o.getBasket().keySet()) {
			for (Dish dish : dishList) {
				if (dish.toString().equals(d.toString())) {
					if (stockManager.getManager().getQuantity(dish) >= (Integer)o.getBasket().get(d)) {
						status = true;
					}else {
						status = false;
						break;
					}
				}else continue;
			}if (!status) {
				break;
			}
		}return status;
	}
			
		
	
	public void delivery(){
		/* Loop through the orderList to check which order has
		 * sufficient dish stock to complete, then deliver the
		 * order with time depending on the speed of the drone
		 */
			if (status.equals(Status.IDLE)) {
				for (Order o : orderList) {
					synchronized(o) {
						if(o.getStatus().equals("WAITING")) {
							if (checkSufficientDishStock(o)) {
								System.out.println("Delivery thread running");
								o.setStatus("DELIVERING");
								Server.getServer().notifyUpdate();
								for (Dish d : o.getBasket().keySet()) {
									synchronized(d) {
										Integer amount = (Integer)o.getBasket().get(d);
										for (Dish dish : stockManager.getDishStock().keySet()) {
											if (dish.toString().equals(d.toString())) {
												stockManager.getManager().addStock(dish, -amount);
												break;
											}
										}
										Server.getServer().notifyUpdate();
									}
								}
								Integer distance = o.getUser().getPostcode().getDistance();
								double time = distance/speed;
								status = Status.DELIVERING;
								o.setStatus("DELIVERING");
								Server.getServer().notifyUpdate();
								try {
									System.out.println("Drone is delivering");
									Thread.sleep((long)(time*10000));
									status = Status.IDLE;
									o.setStatus("COMPLETED");
									Server.getServer().notifyUpdate();
									break;
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								}
						}else continue;
					}
				}
			}
	}
		
	
	@Override
	public void run(){
		// TODO Auto-generated method stub
		while (running) {
			/* if the pauseLock is being held,
			 * thread wait until the pauseLock is notified
			 */
			synchronized(pauseLock){
				if (status.equals(Status.PAUSE)) {
					try {
						pauseLock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						break;
					}
				}else if (status.equals(Status.IDLE)) {
					double prob = Math.random();
					if (prob > 0.5) {
							restock();
					}else {
						delivery();
					}
				}
			}
		}
	}
	//pause by setting the status to PAUSE
	public void pause() {
		status = Status.PAUSE;
	}
	
	public void resume() {
		/* resume the thread running by notifying the 
		 * pauseLock
		 */
		synchronized(pauseLock) {
			status = Status.DELIVERING;
			pauseLock.notifyAll();
		}
	}
	
	public String getStatus() {
		return status.name();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
