/* Created by Ching Man Yung, 17/5/2018
 * 
 */

package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class stockManager implements Serializable{
	/* stockManager is responsible for monitoring the stock level of
	 * the dish stock and ingredient stock
	 */
	static stockManager manager = new stockManager();
	static Map<Ingredient, Number> ingredientStock = new HashMap<Ingredient, Number>();
	static Map<Dish, Number> dishStock = new HashMap<Dish, Number>();
	
	public static stockManager getManager() {
		return manager;
	}
	
	
	public static Map<Ingredient, Number> getIngredientStock() {
		return ingredientStock;
	}
	
	public static Map<Dish, Number> getDishStock() {
		return dishStock;
	}
	
	//method to add the stock to ingredientStock
	public void addStock(Ingredient i, Integer quantity) {
		for (Ingredient ingredient : Ingredient.getIngredientList()) {
			if (ingredient.equals(i)) {
				if (ingredientStock.containsKey(ingredient)) {
					ingredientStock.put(ingredient, (Integer)ingredientStock.get(ingredient)+quantity);
				}else{
					ingredientStock.put(ingredient, quantity);
				}
			}
		}	
	}

	//method to add the stock to dishStock
	public void addStock(Dish d, Integer quantity) {
		for (Dish dish : Dish.getDishList()) {
			if (dish.equals(d)) {
				if (dishStock.containsKey(dish)) {
					dishStock.put(dish, (Integer)dishStock.get(dish)+quantity);
				}else dishStock.put(dish, quantity);
			}
		}
	}
	
	public ArrayList<Dish> getAvailableDish() {
		return Dish.getDishList();
	}
	
	public Integer getQuantity(Dish d) {
		Integer quantity = 0;
		for (Dish dish : Dish.getDishList()) {
			if (dish.equals(d)) {
				quantity = (Integer)dishStock.get(dish);
				break;
			}
		}return quantity;
	}
	
	public Integer getQuantity(Ingredient i) {
		Integer quantity = 0;
		for (Ingredient in : Ingredient.getIngredientList()) {
			if (in.equals(i)) {
				quantity = (Integer)ingredientStock.get(in);
				break;
			}
		}return quantity;
	}
	
	public void addStock(String i, Integer integer) {
		for (Ingredient in : Ingredient.getIngredientList()) {
			if (in.getName().equals(i)) {
				addStock(in, integer);
				
				break;
			}
		}
		for (Dish d : Dish.getDishList()) {
			if (d.getName().equals(i)) {
				addStock(d, integer);
				break;
			}
		}
	}
	
	//return true when the quantity of ingredient is lower than the threshold value
	public boolean checkRestock(Ingredient i) {
		return (getQuantity(i) <= (Integer)i.getThreshold());
	}
	
	//return true when the quantity of dish is lower than the threshold value
	public boolean checkRestock(Dish d) {
		return (getQuantity(d) <= (Integer)d.getThreshold());
	}
	
}
