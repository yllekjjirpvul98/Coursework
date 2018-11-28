/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order extends Model implements Serializable{
	/* Order class implements Serializable so that it can
	 * be deserialized and written into files
	 */
	
	User user = null;
	Map<Dish, Number> Basket;
	Status status = Status.WAITING;
	static ArrayList<Order> orderList = new ArrayList<Order>();
	
	/* Enum is used to indicate the status of the order
	 */
	enum Status{
		COMPLETED, DELIVERING, PREPARING, WAITING
	}
	
	public Order(Map<Dish, Number> basket, User user){
		setName("Order");
		this.Basket = basket;
		this.user = user;
		orderList.add(this);
	}
	
	public Order(Order order) {
		this.Basket = order.getBasket();
		for (User u : User.getUserList()) {
			if (order.getName().equals(user.getName())) {
				this.user = u;
			}
		}
	}
	
	public static ArrayList<Order> getOrderList(){
		return orderList;
	}
	
	public String getStatus() {
		return status.name();
	}
	
	public void setStatus(String string) {
		status = Status.valueOf(string);
	}
	
	public User getUser() {
		return user;
	}
	
	public Map<Dish, Number> getBasket(){
		if (Basket == null) { System.out.println("Basket is null!"); }
		return Basket;
	}
	
	public Integer calculateCost() {
		Integer cost = 0;
		for (Dish dish : Basket.keySet()) {
			for (Dish d : Dish.getDishList()) {
				Integer price = (Integer) d.getPrice();
				Integer quantity = (Integer) Basket.get(d);
				cost += (price * quantity);
				break;
			}	
		}return cost;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public boolean getComplete() {
		return status.equals(Status.COMPLETED);
	}

}
