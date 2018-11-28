/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User extends Model implements Serializable{
	String username;
	private String password;
	public Postcode postcode;
	public String address;
	public Map<Dish, Number> Basket = new HashMap<Dish, Number>(); 
	public ArrayList<Order> orders = new ArrayList<Order>();
	
	static ArrayList<User> users = new ArrayList<User>();
	
	public User(String username, String password, String address, Postcode postcode){
		setName(username);
		this.password = password;
		this.postcode = postcode;
		this.address = address;
		users.add(this);
	}
	
	
	public ArrayList<Order> getOrders() {
		return orders;
	}
	
	public Postcode getPostcode() {
		return postcode;
	}
	
	public static ArrayList<User> getUserList() {
		return users;
	}
	
	//method to clear the order list made by user
	public void resetOrderList() {
		orders = new ArrayList<Order>();
	}
	
	//method to remove order from the order list visible in user interface
	public void removeOrder(Order order) {
		for (Order orderToRemove : orders) {
			if (orderToRemove.equals(order)) {
				orders.remove(orderToRemove);
				break;
			}
		}
	}
	
	//make order by creating a new instance of order using the basket
	public Order makeOrder() {
		Order order = new Order(Basket, this);
		orders.add(order);
		return order; 
	}
	
	//clear Basket by changing the reference to a new hash map
	public void clearBasket() {
		Basket = new HashMap<Dish, Number>(); 
	}
	
	//method to add new dish of certain quantity to the basket
	public void addToBasket(Dish dish, Number quantity){
				if (Basket.containsKey(dish)) {
					Basket.put(dish, (int)Basket.get(dish)+(int)quantity);
				}else Basket.put(dish,(Integer) quantity);
	}
	
	public void updateBasket(Dish dish, Number quantity) {
				if ((int)quantity == 0) {
					Basket.remove(dish);
				}else {
					Basket.put(dish, (int)quantity);
				}
	}
	
	public Map<Dish, Number> getBasket(){
		return Basket;
	}
	
	public String getPassword() {
		return password;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
