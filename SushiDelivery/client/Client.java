/* Created by Ching Man Yung, 17/5/2018
 * 
 */

package client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import common.Dish;
import common.Message;
import common.Message.MessageType;
import common.Model;
import common.Order;
import common.Postcode;
import common.UpdateEvent;
import common.UpdateListener;
import common.User;
import common.stockManager;
//import common.ClientServer;
import common.Comms;
import common.DataPersistance;


public class Client implements ClientInterface {
	
	ArrayList<UpdateListener> updateListeners = new ArrayList<UpdateListener>();
	stockManager manager = stockManager.getManager();
	boolean logInSuccess;
	Comms com = Comms.getCommunicationLayer();
	User currentUser = null;
	ArrayList<Dish> dishList = new ArrayList<Dish>();
	ArrayList<Postcode> postcode = new ArrayList<Postcode>();
	
	
	public Client(){
		com.runClient();
	}
	
	@Override
	//user send registration message to the server side
	public User register(String username, String password, String address, Postcode postcode) {
		// TODO Auto-generated method stub
		com.clientSendMessage(new Message(MessageType.REGISTRATION, username, password, address, postcode.getName()));
		//server will pass back the user created in the server
		currentUser = (User) com.receiveMessage();
		return currentUser;
	}

	@Override
	//when user trying to login
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		//client send message to requesting to login 
		com.clientSendMessage(new Message(MessageType.LOGIN, username, password));
		//server return the user from the server side
		User user = (User) com.receiveMessage();
		currentUser = user;
		return currentUser;
	}

	@Override
	public List<Postcode> getPostcodes() {
		// TODO Auto-generated method stub
		//send message to server to request the postcode list
		com.clientSendMessage(new Message(MessageType.REQUESTPOSTCODE));
		//server side will return the postcode list
		postcode = (ArrayList<Postcode>) com.receiveMessage();
		return postcode;
	}
	

	@Override
	public List<Dish> getDishes() {
		// TODO Auto-generated method stub
		//send message to server to request dishlist
		com.clientSendMessage(new Message(MessageType.REQUESTDISH));
		//server will return dishlist
		dishList = (ArrayList<Dish>) com.receiveMessage();
		return (List<Dish>) dishList;
	}

	@Override
	//get description of the dish
	public String getDishDescription(Dish dish) {
		// TODO Auto-generated method stub
		String description = null;
		for (Dish d: getDishes()) {
			if (d.equals(dish)) {
				description = d.getDescription();
			}
		}return description;
	}

	@Override
	//get the price of the dish
	public Number getDishPrice(Dish dish) {
		// TODO Auto-generated method stub
		Number price = null;
		for (Dish d: getDishes()) {
			if (d.equals(dish)) {
				price = d.getPrice();
			}
		}return price;
	}

	@Override
	//get the basket of the user
	public Map<Dish, Number> getBasket(User user) {
		// TODO Auto-generated method stub
		return user.getBasket();
	}

	@Override
	//get basket cost of the basket
	public Number getBasketCost(User user) {
		// TODO Auto-generated method stub
		int cost = 0;
		Map<Dish, Number> Basket = getBasket(user);
		for (Dish dish : Basket.keySet()) {
			cost += ((int)getDishPrice(dish))*(int)Basket.get(dish);
		}return cost;
	}

	@Override
	//add new dish to the basket
	public void addDishToBasket(User user, Dish dish, Number quantity) {
		// TODO Auto-generated method stub
		user.addToBasket(dish, quantity);
		notifyUpdate();
	}
	
	@Override
	//update dish in basket
	public void updateDishInBasket(User user, Dish dish, Number quantity) {
		// TODO Auto-generated method stub
				user.updateBasket(dish, quantity);
				notifyUpdate();
	}

	@Override
	//user check out basket and send the new order to the server
	public Order checkoutBasket(User user) {
		// TODO Auto-generated method stub
				Order order = user.makeOrder();
				com.clientSendMessage(new Message(MessageType.ORDERMADE, order));
				clearBasket(user);
				notifyUpdate();
				return order;
	}

	@Override
	//clear the basket 
	public void clearBasket(User user) {
		// TODO Auto-generated method stub
				user.clearBasket();
				notifyUpdate();
	}

	@Override
	//get the order list made by the user
	public List<Order> getOrders(User user) {
		return user.getOrders();
	}

	@Override
	//return true if the status of order is COMPLETED
	//return false otherwise
	public boolean isOrderComplete(Order order) {
		// TODO Auto-generated method stub
		return order.getStatus().equals("COMPLETED");

	}

	@Override
	//fetch the status of the order
	public String getOrderStatus(Order order) {
		// TODO Auto-generated method stub
		return order.getStatus();
	}

	@Override
	//calculate the order cost 
	public Number getOrderCost(Order order) {
		// TODO Auto-generated method stub
		int cost = (int) 0;
			Map<Dish, Number> Basket = order.getBasket();
			for (Dish dish : Basket.keySet()) {
				cost += ((int)getDishPrice(dish))*(int)Basket.get(dish);
			}return cost;
	}

	@Override
	//cancel the order by removing the orders list in user
	//and send message to inform the user that the order is cancelled
	public void cancelOrder(Order order) {
		// TODO Auto-generated method stub
		order.getUser().removeOrder(order);
		com.clientSendMessage(new Message(MessageType.ORDERCANCELLED, order));
		notifyUpdate();
		
	}

	@Override
	//add updatelistener to the listener list
	public void addUpdateListener(UpdateListener listener) {
		// TODO Auto-generated method stub
		updateListeners.add(listener);
	}

	@Override
	//notify listeners when there is an updated event
	public void notifyUpdate() {
		// TODO Auto-generated method stub	
		for (UpdateListener listener : updateListeners) {
			listener.updated(new UpdateEvent());
		}
	}
	
}
