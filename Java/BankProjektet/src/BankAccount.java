
public class BankAccount {
	private Customer holder;
	private double moneyInAccount = 0;
	private int accountNumber;
	
	public BankAccount(String holderName, long holderId) {
		this.holder = new Customer(holderName, holderId);
	}
	
	public BankAccount(Customer holder) {
		this.holder = holder;
	}
	
	public Customer getHolder() {
		return holder;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public double getAmount() {
		return moneyInAccount;
	}
	
	public void deposit(double amount) {
		moneyInAccount += amount;
	}
	
	public void withdraw(double amount) {
		moneyInAccount -= amount;
	}
	
	public String toString() {
		return "konto " + accountNumber + " (" + holder.toString() + "): " + moneyInAccount;
	}
}
