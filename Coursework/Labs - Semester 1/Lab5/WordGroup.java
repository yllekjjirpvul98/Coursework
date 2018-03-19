import java.util.HashSet;
import java.util.HashMap;

public class WordGroup {
	public String words;
	
	/*Constructor with the sentence as the parameter */
	public WordGroup(String words) {
		
		/* this will convert the words to lowercase */
		this.words = words.toLowerCase();
	}
	
	public String[] getWordArray() {
	
	/* create a string array by using the split method */
		String[] wordarray = words.split(" ");
		return wordarray;
	}
	
	public HashSet<String> getWordSet(WordGroup wordgroup) {
		
		/* create a hashset called hashset to store each string from both array */
		HashSet<String> hashset = new HashSet<String>();
		
		for (String s:this.getWordArray()) {
			hashset.add(s);
		}
		for (String s:wordgroup.getWordArray()) {
			hashset.add(s);
		}
		return hashset;
	}
	
	public HashMap<String, Integer> getWordCounts(){
		
		/* create a hashmap called hashmap to store the key-value pair */
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		
		for (String s:this.getWordArray()) {
			if (!(hashmap.containsKey(s))) {
				
			/* if the key does not already exist in the hashmap */
				hashmap.put(s,1);
			}else {
				
				/* increment the value of the key by 1 */
				hashmap.put(s, hashmap.get(s) + 1);
			}
		}return hashmap;
	}
}
