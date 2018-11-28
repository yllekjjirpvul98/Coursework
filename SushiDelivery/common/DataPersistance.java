/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import common.Staff.Status;
import server.Configuration;
import server.Server;

public class DataPersistance {
	
	private ArrayList<Supplier> supplierList = Supplier.getSupplierList();
	private ArrayList<Ingredient> ingredientList = Ingredient.getIngredientList();
	private ArrayList<Dish> dishList = Dish.getDishList();
	private ArrayList<Order> orderList = Order.getOrderList();
	private ArrayList<Staff> staffList = Staff.getStaffList();
	private ArrayList<Drone> droneList = Drone.getDroneList();
	private HashMap<Dish, Number> dishStock = (HashMap<Dish, Number>) stockManager.getDishStock();
	private HashMap<Ingredient, Number> ingredientStock = (HashMap<Ingredient, Number>) stockManager.getIngredientStock();
	private ArrayList<User> userList = User.getUserList();
	private ArrayList<Postcode> available = (ArrayList<Postcode>) Postcode.getAvailablePostcode();
	ArrayList<Object> objects = new ArrayList<Object>();
	static DataPersistance data;
	private stockManager manager = stockManager.getManager();
	File file;
	
	public DataPersistance(){
		data = this;
	}
	
	public static DataPersistance getDataPersistance() {
		return data;
	}
	
	public File getFile() {
		return file;
	}
	
	/* reset all the data stored in the arrays by clearing them and
	 * stop all running threads by interrupting them
	 */
	public void reset() {
		Supplier.getSupplierList().clear();
		Ingredient.getIngredientList().clear();
		Dish.getDishList().clear();
		Order.getOrderList().clear();
		for (Staff staff : Staff.getStaffList()) {
			for (Thread t : Thread.getAllStackTraces().keySet()) {
				if (staff.toString().equals(t.getName())) {
					System.out.println("Stop the thread");
					t.interrupt();
					break;
				}
			}
		}
		Staff.getStaffList().clear();
		for (Drone drone : Drone.getDroneList()) {
			for (Thread t : Thread.getAllStackTraces().keySet()) {
				if (Integer.toString((int)drone.getSpeed()).equals(t.getName())) {
					System.out.println("Stop the thread");
					t.interrupt();
					break;
				}
			}
		}
		Drone.getDroneList().clear();
		stockManager.getDishStock().clear();
		User.getUserList().clear();
		stockManager.getIngredientStock().clear();
		Postcode.getAvailablePostcode().clear();
		Server.getServer().notifyUpdate();
	}
	
	//save the arrays by writing them into the data.txt
	public void save() {
		FileOutputStream fos;
		try {
			System.out.println("Data is saved");
			file = new File("data.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for (Supplier supplier : Supplier.getSupplierList()) {
				writer.write("SUPPLIER:" +supplier.getName()+":"+supplier.getDistance()+"\n");
			}
			for (Drone drone : Drone.getDroneList()) {
				writer.write("DRONE:"+ Integer.toString((int)drone.getSpeed()) + "\n");
			}
			for (Ingredient ingredient : ingredientList) {
				writer.write("INGREDIENT:" + ingredient.getName() + ":" + ingredient.getUnit() + ":" + ingredient.getSupplier().toString() + ":" + Integer.toString((int)ingredient.getThreshold()) + ":" + Integer.toString((int)ingredient.getRestocking()) + "\n");
			}
			for (Dish dish : Dish.getDishList()) {
				writer.write("DISH:" + dish.getName() + ":" + dish.getDescription() + ":" + dish.getPrice() + ":" + dish.getThreshold() + ":" + dish.getRestocking() + ":");
				for (Ingredient ingredient : dish.getIngredients().keySet()) {
					writer.write(dish.getIngredients().get(ingredient) + " * " + ingredient.toString() + ",");
					if (ingredient.equals(new ArrayList(dish.getIngredients().keySet()).get(dish.getIngredients().keySet().size() - 1))){
						writer.write(dish.getIngredients().get(ingredient) + " * " + ingredient.toString());
					}
				}writer.write("\n");
			}
			for (User user : User.getUserList()) {
				writer.write("USER:" + user.getName() + ":" + user.getPassword() + ":" + user.address + ":" + user.getPostcode().toString() + "\n");
			}
			for (Postcode p : Postcode.getAvailablePostcode()) {
				writer.write("POSTCODE:" + p.getName() + ":" +p.getDistance() + "\n");
			}
			for (Staff s : Staff.getStaffList()) {
				writer.write("STAFF:" + s.getName() + "\n");
			}
			for (Ingredient ingredient : stockManager.getIngredientStock().keySet()) {
				writer.write("STOCK:" + ingredient.getName() + ":" + stockManager.getIngredientStock().get(ingredient) + "\n");
			}
			for (Dish dish : stockManager.getDishStock().keySet()) {
				writer.write("STOCK:" + dish.getName() + ":" + stockManager.getDishStock().get(dish) + "\n");
			}
			for (Order order : Order.getOrderList()) {
				writer.write("ORDER:" + order.getUser().toString() +":");
				for (Dish dish : order.getBasket().keySet()) {
					writer.write(order.getBasket().get(dish) + " * " + dish.toString() + ",");
					if (dish.equals(new ArrayList(order.getBasket().keySet()).get(order.getBasket().keySet().size() - 1))){
						writer.write(order.getBasket().get(dish) + " * " + dish.toString());
					}writer.write("\n");
				}
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//read the data.txt and load the data using configuration
	public void read() {
		Configuration c = new Configuration("data.txt");
		try {
			c.parse();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	
	
}
