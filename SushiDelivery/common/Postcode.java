/* Created by Ching Man Yung, 17/5/2018
 * 
 */
package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Postcode extends Model implements Serializable{
	String name;
	static ArrayList<Postcode> available = new ArrayList<Postcode>();
	Integer distance;
	
	public Postcode(String postcode, Integer distance){
		this.name = postcode;
		this.distance = distance;
		available.add(this);
	}

	public Integer getDistance() {
		return distance;
	}
	
	public static List<Postcode> getAvailablePostcode() {
		return available;
	}
	
	/* To validate whether the postcode is in the correct
	 * structure
	 */
	public boolean validation() {
		String regex = "^[A-Z] {1,2} [0-9][0-9][0-]? [0-9][A-Z]{2}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(this.getName());
		return matcher.matches();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
