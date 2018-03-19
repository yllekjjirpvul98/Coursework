import java.util.HashMap;
import java.util.HashSet;

public class Main {
	public static void part1() {
		WordGroup wordgroup1 = new WordGroup("You can discover more about a person in an hour of play than in a year of conversation");
		WordGroup wordgroup2 = new WordGroup("When you play play hard when you work don't play at all");
		
		/* create two string array to store each string in the sentences */
		String[] wordgrouparray1 = wordgroup1.getWordArray();
		String[] wordgrouparray2 = wordgroup2.getWordArray();
		
		/*two for loops to print out the content of wordgrouparray */
		for (String s:wordgrouparray1) {
			System.out.println(s);
		}
		for (String s:wordgrouparray2) {
			System.out.println(s);
		}  
	}//WordGroupArray1
	
	public static void part2() {
		WordGroup wordgroup1 = new WordGroup("You can discover more about a person in an hour of play than in a year of conversation");
		WordGroup wordgroup2 = new WordGroup("When you play play hard when you work don't play at all");
		
		/* create a hash set of type string */
		HashSet<String> hashset = new HashSet<String>();
		hashset = wordgroup1.getWordSet(wordgroup2);
		
		for (String word:hashset) {
		/*the word print should contains no duplicates */
			System.out.println(word);
		}
	}
	
	public static void part3() {
		WordGroup wordgroup1 = new WordGroup("You can discover more about a person in an hour of play than in a year of conversation");
		WordGroup wordgroup2 = new WordGroup("When you play play hard when you work don't play at all");
		
		/* map 1 is used to store string-count sets of wordgroup1 */ 
		HashMap<String, Integer> map1 = new HashMap<String, Integer>();
		map1 = wordgroup1.getWordCounts();
		
		/* map 2 is used to store string-count sets of wordgroup2 */ 
		HashMap<String, Integer> map2 = new HashMap<String, Integer>();
		map2 = wordgroup2.getWordCounts();
		
		/* map is used to store combined string-count sets of both wordgroups */
		HashSet<String> hashset = new HashSet<String>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		/* for loops to print out key and value of both hashmaps */
		for (String key:map1.keySet()) {
			System.out.println(key+ " " + map1.get(key));
		}
		for (String key:map2.keySet()) {
			System.out.println(key+" "+map2.get(key));
		} 		
		
		for (String key:hashset) {
			/* Integer used to store sum of word counts from both groups */
			Integer value = 0;
			
			if (map1.containsKey(key)) {
				value += map1.get(key);
			}if (map2.containsKey(key)) {
				value += map2.get(key);
			}map.put(key, value);
		}
		
		/* print out the key and value pair of the combined hash map */
		for (String key:map.keySet()) {
			System.out.println(key + " "+ map.get(key));
		}
	}
	public static void main(String[] args) {
		part1();
		part2();
		part3();
	}
}
