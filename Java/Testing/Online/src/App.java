import java.util.Locale;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int wantedMoney = scan.nextInt();
        int nbrDays = calculateNumberOfDays(wantedMoney);
        System.out.println(nbrDays);
    }
    
    public static int calculateNumberOfDays(int wantedMoney) {
    	int salary = 1;
    	int money = 0;
    	int days = 0;
    	
    	while(money < wantedMoney) {
    		money += salary;
    		salary *= 2;
    		days++;
    	}
    	
    	return days;
    }
}
