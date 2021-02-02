package textproc;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String,Integer>> {

	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		int number1 = o2.getValue();
		int number2 = o1.getValue();
		
		if(number1 > number2) {
			return 1;
		} else if(number1 < number2) {
			return -1;
		}
		
		return o1.getKey().compareToIgnoreCase(o2.getKey());
	}
}
