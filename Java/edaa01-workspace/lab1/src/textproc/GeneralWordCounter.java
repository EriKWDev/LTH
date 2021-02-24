package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {

	private Map<String, Integer> wordCountMap = new TreeMap<String, Integer>();
	private Set<String> wordsToIgnoreSet;
	
	public GeneralWordCounter(Set<String> wordsToIgnoreSet) {
		this.wordsToIgnoreSet = wordsToIgnoreSet;
	}
	
	public void process(String word) {
		if(wordsToIgnoreSet.contains(word)) {
			return;
		}
		
		if(wordCountMap.get(word) == null) {
			wordCountMap.put(word, 1);
			return;
		}
		
		wordCountMap.put(word, wordCountMap.get(word) + 1);
	}
	
	public List<Map.Entry<String, Integer>> getWordList() {
		return new ArrayList<>(wordCountMap.entrySet());
	}
	
	public void report() {
		Set<Map.Entry<String, Integer>> wordSet = wordCountMap.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		
		for(int i = 0; i < 30; i++) {
			Entry<String, Integer> wordEntry = wordList.get(i);
			System.out.println(wordEntry.getKey() + ": " + wordEntry.getValue());
		}
		
		/** // Gamla koden som skrev ut alla under 200
		for(String word : wordCountMap.keySet()) {
			if(wordCountMap.get(word) < 200) {
				continue;
			}
			
			System.out.println(word + ": " + wordCountMap.get(word));
		}**/
	}
}
