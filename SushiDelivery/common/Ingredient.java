/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

import java.io.Serializable;
import java.util.ArrayList;

public class Ingredient extends Model implements Serializable{
	
/* Ingredient class implements Serializable so that it can be
 * deserialized when writing into the files
 */
	
	String unit;
	Supplier supplier;
	Integer threshold;
	Integer restocking;
	static ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	
	public Ingredient(String name, String unit, Supplier supplier, Integer threshold, Integer restocking){
		setName(name);
		setUnit(unit);
		setSupplier(supplier);
		setThreshold(threshold);
		setRestocking(restocking);
		ingredientList.add(this);
	}
	
	public static ArrayList<Ingredient> getIngredientList() {
		return ingredientList;
	}

	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
			return name;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public Number getRestocking() {
		return restocking;
	}
	
	public Number getThreshold() {
		return threshold;
	}
	
	public Supplier getSupplier() {
			return supplier;
	}
	
	public void setThreshold(Integer threshold) {
		notifyUpdate("threshold",this.threshold,threshold);
		this.threshold = threshold;
	}
	
	public void setRestocking(Integer restocking) {
		notifyUpdate("restocking", this.restocking, restocking);
		this.restocking = restocking;
	}
	
	public void setSupplier(Supplier supplier) {
		notifyUpdate("supplier",this.supplier,supplier);
		this.supplier = supplier;
	}
	
	public void setUnit(String unit) {
		notifyUpdate("unit",this.unit,unit);
		this.unit = unit;
	}

}
