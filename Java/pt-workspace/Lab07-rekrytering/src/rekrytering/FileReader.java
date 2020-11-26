package rekrytering;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileReader {

	/**
	 * Returnerar max nbrOfRows rader från filen som en vektor av Applicant-objekt.
	 * Läser i filen tills det inte finns fler rader eller tills man läst nbrOfRows
	 * rader (det som inträffar först). 
	 * Returnerar null om filen inte finns.
	 */
	public static Applicant[] readFromFile(String fileName, int nbrOfRows) {
		Scanner scanner;
		try {
			scanner = new Scanner(new File(fileName), "utf-8");
		} catch (FileNotFoundException e) {
			System.err.println("File not found" + fileName);
			e.printStackTrace();
			return null;
		}
		
		ArrayList<Applicant> applicantList = new ArrayList<Applicant>();
		
		//Här kan du använda Scannern för att läsa från filen fileName.
		//Varje rad motsvarar en Applicant. Kontrollera vad Applicants konstruktor kräver
		//Alla Applicant-objekt (max nbrOfRows stycken) ska lagras i en Applicant-vektor och returneras på slutet
		
		for (int n = 0; n < nbrOfRows && scanner.hasNextLine(); n++) {
		   String line = scanner.nextLine();
		   
		   if(line.isBlank()) {
			   continue;
		   }
		   
		   String[] spaceSplitLine = line.split(" ");
		   String name = spaceSplitLine[0] + " " + spaceSplitLine[1];
		   String gradesAsString = line.substring(name.length()).trim();
		   
		   applicantList.add(new Applicant(name, gradesAsString));
		}
		
		return applicantList.toArray(new Applicant[0]);
	}
}
