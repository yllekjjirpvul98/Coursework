/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import common.Comms;
import common.DataPersistance;
import common.Dish;
import common.Drone;
import common.Ingredient;
import common.Order;
import common.Postcode;
import common.ShutdownHook;
import common.Staff;
import common.Supplier;
import common.UpdateEvent;
import common.UpdateListener;
import common.User;
import common.stockManager;

public class Server implements ServerInterface{

	boolean enabledIngredient;
	boolean enabledDish;
	
	List<Supplier> supplierList = Supplier.getSupplierList();
	List<Ingredient> ingredientList = Ingredient.getIngredientList();
	List<Dish> dishList = Dish.getDishList();
	List<Order> orderList = Order.getOrderList();
	List<Staff> staffList= Staff.getStaffList();
	List<Drone> droneList =Drone.getDroneList();
	Map<Dish, Number> dishStock = stockManager.getDishStock();
	Map<Ingredient, Number> ingredientStock = stockManager.getIngredientStock();
	Comms com = Comms.getCommunicationLayer();
	ArrayList<UpdateListener> updateListeners = new ArrayList<UpdateListener>();
	static Server server;
	DataPersistance data = new DataPersistance();
	
	public Server() {
		com.runServer();
		data.read();
		try {
			if (data.getFile() != null) {
			Files.deleteIfExists(data.getFile().toPath());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server = this;
		Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		notifyUpdate();
	}
	
	public static Server getServer() {
		return server;
	}
	
	public void setServer(Server server) {
		this.server = server;
	}
	
	@Override
	/* method to load the configuration file, which will always reset the arrays
	 * before the configuration
	 */
	public void loadConfiguration(String filename) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Configuration con = new Configuration(filename);
		data.reset();
		con.parse();
		notifyUpdate();
	}

	@Override
	public void setRestockingIngredientsEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		enabledDish = enabled;
		for (Drone drone : droneList) {
			if (!enabledDish) {
				if (drone.getStatus().equals("RESTOCKING")) {
					drone.pause();
				}
			}else drone.resume();
		}
		notifyUpdate();
	}

	@Override
	public void setRestockingDishesEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		enabledIngredient = enabled;
		for (Staff staff : staffList) {
			if (!enabledIngredient) {
				staff.pause();
			}else staff.resume();
		}notifyUpdate();
	}

	@Override
	public void setStock(Dish dish, Number stock) {
		// TODO Auto-generated method stub
		for (Dish d : dishList) {
			if (d.equals(dish)) {
				dishStock.put(d, (Integer) stock);
				notifyUpdate();
			}
		}
	}

	@Override
	public void setStock(Ingredient ingredient, Number stock) {
		// TODO Auto-generated method stub
		for (Ingredient i : ingredientList) {
			ingredientStock.put(i, (Integer)stock);
			notifyUpdate();
		}
	}

	@Override
	public List<Dish> getDishes() {
		// TODO Auto-generated method stub
		return Dish.getDishList();
	}

	@Override
	public Dish addDish(String name, String description, Number price, Number restockThreshold, Number restockAmount) {
		// TODO Auto-generated method stub
		Dish d = new Dish(name, description, (Integer)price, (Integer)restockThreshold, (Integer)restockAmount);
		stockManager.getDishStock().put(d, 0);
		notifyUpdate();
		return d;
	}

	@Override
	public void removeDish(Dish dish) throws UnableToDeleteException {
		// TODO Auto-generated method stub
		for (Dish d : getDishes()) {
			if (dish.equals(d)) {
				stockManager.getDishStock().remove(d);
				getDishes().remove(d);
				notifyUpdate();
				break;
			}
		}
	}

	@Override
	public void addIngredientToDish(Dish dish, Ingredient ingredient, Number quantity) {
		// TODO Auto-generated method stub
		for (Dish d : dishList) {
			if (dish.equals(d)) {
				dish.editIngredient(ingredient, quantity);
				notifyUpdate();
				break;
			}
		}
	}

	@Override
	public void removeIngredientFromDish(Dish dish, Ingredient ingredient) {
		// TODO Auto-generated method stub
		for (Dish d : dishList) {
			if (d.equals(dish)) {
				d.removeIngredient(ingredient);
				notifyUpdate();
				break;
			}
		}
	}

	@Override
	public void setRecipe(Dish dish, Map<Ingredient, Number> recipe) {
		// TODO Auto-generated method stub
		for (Dish d : dishList) {
			if (dish.equals(d)) {
				d.setIngredients(recipe);
				notifyUpdate();
				break;
			}
		}
	}

	@Override
	public void setRestockLevels(Dish dish, Number restockThreshold, Number restockAmount) {
		// TODO Auto-generated method stub
		for (Dish d : dishList) {
			if (dish.equals(d)) {
				d.setRestocking((Integer) restockAmount);
				d.setThreshold((Integer) restockThreshold);
				notifyUpdate();
				break;
			}
		}
	}

	@Override
	public Number getRestockThreshold(Dish dish) {
		// TODO Auto-generated method stub
		Number restock = null;
		for (Dish d : dishList) {
			if (dish.equals(d)) {
				restock = d.getThreshold();
				notifyUpdate();
				break;
			}
		}return restock;
	}

	@Override
	public Number getRestockAmount(Dish dish) {
		// TODO Auto-generated method stub
		Number restock = null;
		for (Dish d : dishList) {
			if (dish.equals(d)) {
				restock = d.getRestocking();
				notifyUpdate();
				break;
			}
		}return restock;
	}

	@Override
	public Map<Ingredient, Number> getRecipe(Dish dish) {
		 Map<Ingredient, Number> recipe = null;
			for (Dish d : dishList) {
				if (d.equals(dish)) {
					recipe = d.getIngredients();
					notifyUpdate();
				}
			}return recipe;	
	}

	@Override
	public Map<Dish, Number> getDishStockLevels() {
		// TODO Auto-generated method stub
		return stockManager.getDishStock();
	}

	@Override
	public List<Ingredient> getIngredients() {
		// TODO Auto-generated method stub
		return (List<Ingredient>) Ingredient.getIngredientList();
	}

	@Override
	public Ingredient addIngredient(String name, String unit, Supplier supplier, Number restockThreshold,
			Number restockAmount) {
		// TODO Auto-generated method stub
		Ingredient i = new Ingredient(name, unit, supplier, (Integer)restockThreshold, (Integer)restockAmount);
		getIngredientStockLevels().put(i, 0);
		System.out.println(ingredientList);
		
		notifyUpdate();
		return i;
	}

	@Override
	public void removeIngredient(Ingredient ingredient) throws UnableToDeleteException {
		// TODO Auto-generated method stub
		for (Ingredient i : Ingredient.getIngredientList()) {
			if (ingredient.equals(i)) {
				stockManager.getIngredientStock().remove(i);
				Ingredient.getIngredientList().remove(i);
				notifyUpdate();
				break;
			}
		}
	}

	@Override
	public void setRestockLevels(Ingredient ingredient, Number restockThreshold, Number restockAmount) {
		// TODO Auto-generated method stub
		for (Ingredient i : ingredientList) {
			if (i.equals(ingredient)) {
				i.setRestocking((Integer) restockAmount);
				i.setThreshold((Integer) restockThreshold);
				notifyUpdate();
				break;
			}
		}
	}

	@Override
	public Number getRestockThreshold(Ingredient ingredient) {
		// TODO Auto-generated method stub
		Number threshold = null;
		for (Ingredient i : ingredientList) {
			if (i.equals(ingredient)) {
				threshold = i.getRestocking();
				break;
			}
		}return threshold;
	}

	@Override
	public Number getRestockAmount(Ingredient ingredient) {
		// TODO Auto-generated method stub
		Number amount = null;
		for (Ingredient i : ingredientList) {
			if (i.equals(ingredient)) {
				amount = ingredient.getRestocking();
				break;
			}
		}return amount;
	}

	@Override
	public Map<Ingredient, Number> getIngredientStockLevels() {
		// TODO Auto-generated method stub
		return stockManager.getIngredientStock();
	}

	@Override
	public List<Supplier> getSuppliers() {
		// TODO Auto-generated method stub
		return Supplier.getSupplierList();
	}

	@Override
	public Supplier addSupplier(String name, Number distance) {
		// TODO Auto-generated method stub
		Supplier supplier = new Supplier(name, (int) distance);
		notifyUpdate();
		return supplier;
	}

	@Override
	public void removeSupplier(Supplier supplier) throws UnableToDeleteException {
		// TODO Auto-generated method stub
		supplierList.remove(supplier);
		notifyUpdate();
	}

	@Override
	public Number getSupplierDistance(Supplier supplier) {
		// TODO Auto-generated method stub
		Number distance = null;
		for (Supplier s : supplierList) {
			if (s.equals(supplier)) {
				distance = s.getDistance();
				break;
			}
		}return distance;
	}
	

	@Override
	public List<Drone> getDrones() {
		// TODO Auto-generated method stub
		return Drone.getDroneList();
	}

	@Override
	public Drone addDrone(Number speed) {
		// TODO Auto-generated method stub
		Drone drone = new Drone((Integer) speed);
		new Thread(drone, Integer.toString((Integer)speed)).start();
		notifyUpdate();
		return drone;
	}

	@Override
	public void removeDrone(Drone drone) throws UnableToDeleteException {
		// TODO Auto-generated method stub
		droneList.remove(drone);
		notifyUpdate();
	}

	@Override
	public Number getDroneSpeed(Drone drone) {
		// TODO Auto-generated method stub
		Number speed = null;
		for (Drone d : droneList) {
			if (d.equals(drone)) {
				speed = d.getSpeed();
			}
		}return speed;
	}

	@Override
	public String getDroneStatus(Drone drone) {
		// TODO Auto-generated method stub
		return drone.getStatus();
	}

	@Override
	public List<Staff> getStaff() {
		// TODO Auto-generated method stub
		return staffList;
	}

	@Override
	public Staff addStaff(String name) {
		// TODO Auto-generated method stub
		Staff staff = new Staff(name);
		new Thread(staff, name).start();
		notifyUpdate();
		return staff;
	}

	@Override
	public void removeStaff(Staff staff) throws UnableToDeleteException {
		// TODO Auto-generated method stub
		staffList.remove(staff);
		for (Thread t : Thread.getAllStackTraces().keySet()) {
			if (t.getName().equals(staff.getName())) {
				t.destroy();
				break;
			}
		}
		notifyUpdate();
	}

	@Override
	public String getStaffStatus(Staff staff) {
		// TODO Auto-generated method stub
		String status = null;
		for (Staff s : staffList) {
			if (s.equals(staff)) {
				status = s.getStatus();
				break;
			}
		}return status;
	}

	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return Order.getOrderList();
	}

	@Override
	public void removeOrder(Order order) throws UnableToDeleteException {
		// TODO Auto-generated method stub
		for (Order o : Order.getOrderList()) {
			if (o.equals(order)) {
				Order.getOrderList().remove(order);
				break;
			}
		}
		notifyUpdate();
	}

	@Override
	public Number getOrderDistance(Order order) {
		// TODO Auto-generated method stub
		Number distance = null;
		for (Order o : orderList) {
			if (o.equals(order)) {
				distance = order.getUser().getPostcode().getDistance();
			}
		}return distance;
	}

	@Override
	public boolean isOrderComplete(Order order) {
		// TODO Auto-generated method stub
		boolean complete = false;
		for (Order o : orderList) {
			if (o.equals(order)) {
				complete = o.getComplete();
			}
		}return complete;
	}

	@Override
	public String getOrderStatus(Order order) {
		// TODO Auto-generated method stub
		String status = null;
		for (Order o : orderList) {
			if (o.equals(order)) {
				status = order.getStatus();
			}
		}return status;
	}

	@Override
	public Number getOrderCost(Order order) {
		// TODO Auto-generated method stub
		Integer cost = 0;
		for (Order o : orderList) {
			if (o.equals(order)) {
				Map<Dish, Number> basket = o.getBasket();
				for (Dish d : basket.keySet()) {
					cost += d.getPrice() * (int)basket.get(d);
				}
			}
		}return cost;
	}	

	@Override
	public List<Postcode> getPostcodes() {
		// TODO Auto-generated method stub
		return Postcode.getAvailablePostcode();
	}

	@Override
	public void addPostcode(String code, Number distance) {
		// TODO Auto-generated method stub
		new Postcode(code, (Integer)distance);
		notifyUpdate();
	}

	@Override
	public void removePostcode(Postcode postcode) throws UnableToDeleteException {
		// TODO Auto-generated method stub
		Postcode.getAvailablePostcode().remove(postcode);
		notifyUpdate();
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return User.getUserList();
	}

	@Override
	public void removeUser(User user) throws UnableToDeleteException {
		// TODO Auto-generated method stub
		User.getUserList().remove(user);
		notifyUpdate();
	}

	@Override
	public void addUpdateListener(UpdateListener listener) {
		// TODO Auto-generated method stub
		updateListeners.add(listener);
	}

	@Override
	public void notifyUpdate() {
		// TODO Auto-generated method stub
		for (UpdateListener listener : updateListeners) {
			listener.updated(new UpdateEvent());
		}
	}


}
