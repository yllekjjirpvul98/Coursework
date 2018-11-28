/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

import java.io.Serializable;
import java.util.ArrayList;

public class Supplier extends Model implements Serializable{
	
	int distance;
	String name;
	static ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
	
	public static ArrayList<Supplier> getSupplierList(){
		return supplierList;
	}
	
	
	public Supplier(String name, int distance){
//		System.out.print(ClientServer.getListeners().size());
		setDistance(distance);
		setName(name);
		supplierList.add(this);
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
			return name;
	}
	
	//get distance of supplier
	public int getDistance() {
			return distance;
	}
	
	public void setDistance(int distance) {
		notifyUpdate("distance",this.distance,distance);
		this.distance = distance;
	}
	
	public void setName(String name) {
		notifyUpdate("name",this.name,name);
		this.name = name;
	}
	

}
