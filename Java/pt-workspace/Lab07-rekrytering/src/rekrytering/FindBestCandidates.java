package rekrytering;

import java.util.ArrayList;
import java.util.Arrays;

public class FindBestCandidates {
	private static final double MIN_AVG_GRADE = 3.0;

	public static void main(String[] args) {
		// Läs från fil (Börja med "applications_small.txt), spara resultatet i en
		// vektor
		Applicant[] applicants = FileReader.readFromFile("applications_all.txt", Integer.MAX_VALUE);
		
		// Skicka in Applicant-vektorn (som du fick i föregående steg) till metoden
		// findBestCandidiates (nedan)
		// Spara resultatet i en vektor
		Applicant[] bestApplicants = FindBestCandidates.findBestCandidates(applicants);
		Arrays.sort(bestApplicants);
		
		// Printa resultatet från findBestCandidates
		for(Applicant applicant : bestApplicants) {
			System.out.println(applicant);
		}
	}

	public static Applicant[] findBestCandidates(Applicant[] applicants) {
		// Hitta alla kandidater som har medelbetyg över MIN_AVG_GRADE
		// Lagra alla dessa kandidater i en vektor, returnera vektorn
		
		ArrayList<Applicant> bestApplicantsList = new ArrayList<Applicant>();
		
		for(Applicant applicant : applicants) {
			if(applicant.getAvgGrade() >= MIN_AVG_GRADE) {
				bestApplicantsList.add(applicant);
			}
		}
		
		return bestApplicantsList.toArray(new Applicant[0]);
	}
}
