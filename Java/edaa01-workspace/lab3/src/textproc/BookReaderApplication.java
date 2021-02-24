package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {
	public static void main(String[] args)  throws FileNotFoundException {
		
		// Läs in undantagsord
		Set<String> wordsToIgnore = new HashSet<String>();
		Scanner ignoreScanner;
		ignoreScanner = new Scanner(new File("undantagsord.txt"));
		
		while(ignoreScanner.hasNext()) {
			String word = ignoreScanner.next().toLowerCase();
			wordsToIgnore.add(word);
		}
		
		GeneralWordCounter wordCounter = new GeneralWordCounter(wordsToIgnore);
		
		Scanner scanner = new Scanner(new File("nilsholg.txt"));
		scanner.findWithinHorizon("\uFEFF", 1);
		scanner.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (scanner.hasNext()) {
			String word = scanner.next().toLowerCase();

			wordCounter.process(word);
		}

		scanner.close();

		BookReaderController bookReaderController = new BookReaderController(wordCounter);
	}
}
