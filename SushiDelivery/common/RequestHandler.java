/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

import common.Message.MessageType;
import server.Server;

public class RequestHandler extends Thread{
	Socket connection;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	RequestHandler(Socket connection){
		this.connection = connection;
		try {
			out = new ObjectOutputStream(connection.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in = new ObjectInputStream(connection.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkDish (Dish d1, Dish d2) {
		boolean dish = false;
		if (d1.toString().equals(d2.toString())) {
			dish = true;
		}
		return dish;
	}
	
	public boolean checkUser(User user1, User user2) {
		boolean status = (user1.toString().equals(user2.toString()));
		return status;
	}
	
	public boolean compareBasket(Map<Dish, Number> basket1, Map<Dish, Number> basket2) {
		boolean status = false;
		if (basket1.keySet().size() == basket2.keySet().size()) {
			for (int i=0; i < basket1.size(); i++) {
			Dish d1 = (Dish) basket1.keySet().toArray()[i];
			Dish d2 = (Dish) basket2.keySet().toArray()[i];
			if (checkDish(d1, d2)) {
				for (Dish dish1 : basket1.keySet()) {
					if (dish1.equals(d1)) {
						d1 = dish1;
					}
				}
				for (Dish dish2 : basket2.keySet()) {
					if (dish2.equals(d2)) {
						d2 = dish2;
					}
				}
				if (basket1.get(d1).equals(basket2.get(d2))) {
					status = true;
				}else {status = false; break;}
			}else {status = false; break;}
			}
		}else status = false;
		return status;
	}
	
	public void run() {
		/* Decide the response and modification to the server according
		 * to the type of message received from the client side
		 */
		try {
			for (Message message; (message = (Message) in.readObject()) != null;) {
				if (message.type.equals(MessageType.LOGIN)) {
					for (User user : User.getUserList()) {
						if (user.getName().equals(message.input)) {
							if (user.getPassword().equals(message.input2)) {
								out.writeObject((String)"Login success!");
								out.writeObject(user);
								break;
							}else {
								out.writeObject("Wrong password!");
								out.writeObject("null");
							}
						}else if (user.equals(User.getUserList().get(User.getUserList().size()-1))){
							out.writeObject((String) "null");
							out.writeObject((String) "Your account is not registered yet!");
							break;
						}
					}
				}else if (message.type.equals(MessageType.REGISTRATION)) {
					User u = null;
					for (Postcode p : Postcode.getAvailablePostcode()) {
						if (p.getName().equals((String)message.input4)) {
							u = new User((String)message.input, (String)message.input2, (String)message.input3, p);
							break;
						}
					}
					for (User user : User.getUserList()) {
						if (user.equals(u)) {
//							System.out.println(user);
							out.writeObject(user);
							break;
						}
					}
					out.writeObject((String)"Successfully registered!");
				}else if (message.type.equals(MessageType.ORDERMADE)) {
					Order.getOrderList().add((Order)message.input);
					Server.getServer().notifyUpdate();
					out.writeObject((String)"Order received");
					
				}else if (message.type.equals(MessageType.ORDERCANCELLED)) {
						Order orderToRemove = (Order) message.input;
						for (Order order : Order.getOrderList()) {
							if (checkUser(order.user, orderToRemove.user)) {
								if (compareBasket(order.Basket, orderToRemove.Basket)) {
									Order.getOrderList().remove(order);
									Server.getServer().notifyUpdate();
									break;
								}
							}

							
						}
						out.writeObject((String)"Order is successfully cancelled!");
				}else if (message.type.equals(MessageType.REQUESTPOSTCODE)) {
					out.writeObject(Postcode.getAvailablePostcode());
					out.writeObject((String)"Postcode sent");
				}else if (message.type.equals(MessageType.REQUESTDISH)) {
//					System.out.println(Dish.getDishList());
//					System.out.println(DataPersistance.getDishList());
					out.writeObject(Dish.getDishList());
					out.writeObject((String)"Dish list sent");
				}
				}Thread.sleep(1);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
