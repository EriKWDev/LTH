import java.util.Scanner;

public class Calculator {
	public static void main(String[] args) {
		System.out.println("Skriv två tal");
		Scanner scan = new Scanner(System.in);
		double nbr1 = scan.nextDouble();
		double nbr2 = scan.nextDouble();
		
		double sum = nbr1 + nbr2;
		System.out.println("Summan av talen är " + sum);
		
		// double diff = Math.abs(nbr1 - nbr2);
		double diff = nbr1 - nbr2;
		System.out.println("Skillnaden mellan talen är " + diff);
		
		double product = nbr1 * nbr2;
		System.out.println("Produkten av talen är " + product);
		
		double quotient = nbr1 / nbr2;
		System.out.println("Kvoten mellan talen är " + quotient);
	}
}
