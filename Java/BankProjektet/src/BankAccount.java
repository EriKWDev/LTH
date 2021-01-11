
public class BankAccount {
	private Customer holder;
	private double moneyInAccount = 0;
	private int accountNumber;
	private static int internalAccountNumberCounter = 1000;
	
	private int getNewAccountNumber() {
		internalAccountNumberCounter++;
		return internalAccountNumberCounter;
	}
	
	public BankAccount(String holderName, long holderId) {
		this.holder = new Customer(holderName, holderId);
		this.accountNumber = getNewAccountNumber();
	}
	
	public BankAccount(Customer holder) {
		this.holder = holder;
		this.accountNumber = getNewAccountNumber();
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
