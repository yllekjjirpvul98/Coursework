/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dish extends Model implements Serializable{
	//do not need to initialise the name variable as it has been done in the Model class
	
	private String description = null;
	private Map<Ingredient, Number> ingredients = new HashMap<Ingredient, Number>();
	private Integer restocking = 0;
	private Integer threshold = 0;
	private Integer price = 0;
	static ArrayList<Dish> dishList = new ArrayList<Dish>();
	
	public Dish(String name, String description, Integer price, Integer threshold, Integer restocking, HashMap<Ingredient, Number> ingredient){
		setName(name);
		setIngredients(ingredient);
		setThreshold(threshold);
		setRestocking(restocking);
		setDescription(description);
		setPrice(price);
		dishList.add(this);
	}
	
	public Dish(String name, String description, Integer price, Integer threshold, Integer restocking){
		setName(name);
		setThreshold(threshold);
		setRestocking(restocking);
		setDescription(description);
		setPrice(price);
		dishList.add(this);
	}
	
	//method to fetch the dishlist
	public static ArrayList<Dish> getDishList() {
		return dishList;
	}
	
	public Integer getPrice() {
		return price;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
			return description;
	}

	public HashMap<Ingredient, Number> getIngredients() {
		// TODO Auto-generated method stub
			return (HashMap<Ingredient, Number>) ingredients;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
			return this.name;
	}
	
	public Number getThreshold() {
		return threshold;
	}
	
	public Number getRestocking() {
		return restocking;
	}
	
	public void editIngredient(Ingredient i, Number quantity) {
		ingredients.put(i, quantity);
	}
	
	public void removeIngredient(Ingredient i) {
		ingredients.remove(i);
	}
	
	public void setPrice(Integer price) {
		notifyUpdate("price",this.price,price);
		this.price = price;
	}
	
	public void setThreshold(Integer threshold) {
		notifyUpdate("threshold",this.threshold,threshold);
		this.threshold = threshold;
	}
	
	public void setRestocking(Integer restocking) {
		notifyUpdate("restocking", this.restocking, restocking);
		this.restocking = restocking;
	}
	
	public void setDescription(String description) {
		notifyUpdate("description",this.description,description);
		this.description = description;
	}
	
	public void setIngredients(Map<Ingredient, Number> ingredients) {
		notifyUpdate("ingredients",this.ingredients,ingredients);
		this.ingredients = ingredients;
	}
	
}
