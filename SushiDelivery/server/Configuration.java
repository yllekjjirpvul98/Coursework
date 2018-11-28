/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import common.DataPersistance;
import common.Dish;
import common.Drone;
import common.Ingredient;
import common.Order;
import common.Postcode;
import common.Staff;
import common.Supplier;
import common.User;
import common.stockManager;

public class Configuration {
	String filename;
	
	public Configuration(String filename){
		this.filename = filename;
	}
	
	public Dish toDish(String dishname) {
		Dish dish = null;
		for (Dish d : Dish.getDishList()) {
			if (d.toString().equals(dishname)) {
				dish = d;
				break;
			}
		}return dish;
	}
	
	public Supplier toSupplier(String supplier) {
		Supplier sup = null;
		for (Supplier s : Supplier.getSupplierList()) {
			if (s.getName().equals(supplier)) {
				sup = s;
			}break;
		}return sup;
	}
	
	public Ingredient toIngredient(String i) {
		Ingredient ingredient = null;
		for (Ingredient in : Ingredient.getIngredientList()) {
			if (in.getName().equals(i)) {
				ingredient = in;
				break;
			}
		}return ingredient;
	}
	
	public Postcode toPostcode(String postcode) {
		Postcode post = null;
		for (Postcode p : Postcode.getAvailablePostcode()) {
			 if (p.getName().equals(postcode)) {
				 post = p;
				 break;
			 }
		 }return post;
		 
	}
	
	/* First create a fileinputstream and datainputstream that direct the data
	 * to the buffered reader, which is responsible for reading the content
	 * of the file and parse the file according to the first part of the line 
	 * which always indicate the type of object it reads in. Create object 
	 * upon the information given
	 */
	public void parse() throws FileNotFoundException {
		// Open the file
		FileInputStream fstream = new FileInputStream(filename);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;

		//Read File Line By Line
		try {
			while ((strLine = br.readLine()) != (null))   {
				if (strLine != "\n") {
				ArrayList<String> line = new ArrayList<String>(Arrays.asList(strLine.split(":")));
				switch(line.get(0)) {
				case "SUPPLIER": 
					new Supplier(line.get(1), Integer.parseInt(line.get(2)));
					break;
				case "INGREDIENT": 
					Supplier sup = null;
					for(Supplier supplier : Supplier.getSupplierList()) {
						if (supplier.getName().equals(line.get(3))) {
							sup = supplier;
						}
					}
					stockManager.getManager().getIngredientStock().put(new Ingredient(line.get(1), line.get(2), sup, Integer.parseInt(line.get(4)), Integer.parseInt(line.get(5))), 0);
					
					break;
				case "DISH":
					String[] pair = line.get(6).split(",");
					HashMap<Ingredient, Number> ingredients = new HashMap<Ingredient, Number>();
					for (String item : pair) {
						String[] items = item.split("\\*");
						Ingredient i = toIngredient(items[1].replaceFirst("\\s+", ""));
						Number quantity = Integer.parseInt(items[0].replaceAll("\\s+",""));
						ingredients.put(i, quantity);
					}
					stockManager.getManager().getDishStock().put(new Dish(line.get(1), line.get(2), Integer.parseInt(line.get(3)), Integer.parseInt(line.get(4)), Integer.parseInt(line.get(5)), ingredients), 0);
					break;
				case "USER":
					Postcode postcode = null;
					for (Postcode pc : Postcode.getAvailablePostcode()) {
						if (pc.getName().equals(line.get(4))) {
							postcode = pc;
						}
					}
					new User(line.get(1), line.get(2), line.get(3), postcode);	
					break;
				case "POSTCODE":
					new Postcode(line.get(1), Integer.parseInt(line.get(2)));
					break;
				case "ORDER":
					String[] s = line.get(2).split(",");
					Map<Dish, Number> basket = new HashMap<Dish, Number>();
					for (String st : s) {
						String[] string = st.split("\\*");
						basket.put(toDish(string[1].replaceFirst("\\s+","")), Integer.parseInt(string[0].replaceAll("\\s+","")));
					}User user = null;
					for (User u : User.getUserList()) {
						if (u.getName().equals(line.get(1))) {
							user = u;
						}
					}new Order(basket, user);
					break;
				case "STOCK":
					stockManager.getManager().addStock(line.get(1), Integer.parseInt(line.get(2)));
					break;
				case "STAFF":
					new Thread(new Staff(line.get(1)), line.get(1)).start();
					break;
				case "DRONE":
					new Thread(new Drone(Integer.parseInt(line.get(1))), Integer.toString(Integer.parseInt(line.get(1)))).start();
					break;
				default:
					continue;
				}
				}
			}
			br.close();
			in.close();
			fstream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Close the input stream
		
	}
}
