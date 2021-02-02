package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {

	private Map<String, Integer> wordCountMap = new TreeMap<String, Integer>();
	
	public MultiWordCounter(String[] words) {
		for(String word : words) {
			wordCountMap.put(word, 0);
		}
	}
	
	public void process(String word) {
		if(wordCountMap.get(word) == null) {
			return;
		}
		
		wordCountMap.put(word, wordCountMap.get(word) + 1);
	}
	
	public void report() {
		for(String word : wordCountMap.keySet()) {
			System.out.println(word + ": " + wordCountMap.get(word));
		}
	}
}
