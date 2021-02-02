package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		
		List<TextProcessor> processors = new ArrayList<TextProcessor>();
		
		// Läs in undantagsord
		Set<String> wordsToIgnore = new HashSet<String>();
		Scanner ignoreScanner = new Scanner(new File("undantagsord.txt"));
		while(ignoreScanner.hasNext()) {
			String word = ignoreScanner.next().toLowerCase();
			wordsToIgnore.add(word);
		}
		
		// Lägg in processorer av olika typer
		processors.add(new SingleWordCounter("nils"));
		processors.add(new SingleWordCounter("norge"));
		processors.add(new MultiWordCounter(REGIONS));
		processors.add(new GeneralWordCounter(wordsToIgnore));
		
		Scanner scanner = new Scanner(new File("nilsholg.txt"));
		scanner.findWithinHorizon("\uFEFF", 1);
		scanner.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (scanner.hasNext()) {
			String word = scanner.next().toLowerCase();

			for(TextProcessor processor : processors) {
				processor.process(word);
			}
		}

		scanner.close();

		for(TextProcessor processor : processors) {
			processor.report();
		}
		
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
	}
}