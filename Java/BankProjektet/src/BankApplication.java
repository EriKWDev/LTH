import java.util.ArrayList;
import java.util.Scanner;

public class BankApplication {
	private Bank bank = new Bank();
	private Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		BankApplication bankApplication = new BankApplication();
		bankApplication.runApplication();
	}
	
	public void runApplication() {
		prettyPrint("Välkommen till Eriks Bank!");
		
		boolean running = true;
		
		while(running) {
			printOptions();
			String input = getInputWithQuestion("val: ");
			
			if(canHandleMainLoopInput(input)) {
				
				int choice = Integer.parseInt(input);
				
				switch(choice) {
					case 1: getAccountsOfCustomerWithId(); break;
					case 2: searchForCustomersByName(); break;
					case 3: deposit(); break;
					case 4: withdraw(); break;
					case 5: internalTransfer(); break;
					case 6: createAccount(); break;
					case 7: removeAccount(); break;
					case 8: printAccounts(); break;
					case 9: running = false; prettyPrint("Hejdå!"); break;
					default: hoppsan("Den funktionen finns inte än!"); break;
				}
				
			} else {
				hoppsan("'" + input + "' är inte ett giltigt val.");
			}
			print();
		}
	}
	
	private void removeAccount() {
		BankAccount account = askForAccount("konto: ");
		if(account == null) {
			return;
		}
		
		bank.removeAccount(account.getAccountNumber());
	}
	
	private void internalTransfer() {
		BankAccount fromAccount = askForAccount("från konto: ");
		if(fromAccount == null) {
			return;
		}
		
		BankAccount toAccount = askForAccount("till konto: ");
		if(toAccount == null) {
			return;
		}
		
		String amountInput = getInputWithQuestion("belopp: ");
		double amount;
		
		try {
			amount = Double.parseDouble(amountInput);
		} catch(NumberFormatException e) {
			hoppsan("'" + amountInput + "' är inte ett giltigt belopp.");
			return;
		}
		
		if(fromAccount.getAmount() - amount < 0) {
			hoppsan("Det verkar inte finnas tillräckligt med pengar på kontot. Saldo: '" + fromAccount.getAmount() + "'");
			return;
		}
		
		fromAccount.withdraw(amount);
		toAccount.deposit(amount);
		
		print(fromAccount.toString());
	}
	
	private void deposit() {
		BankAccount account = askForAccount("konto: ");
		if(account == null) {
			return;
		}
		
		String amountInput = getInputWithQuestion("belopp: ");
		double amount;
		
		try {
			amount = Double.parseDouble(amountInput);
		} catch(NumberFormatException e) {
			hoppsan("'" + amountInput + "' är inte ett giltigt belopp.");
			return;
		}
		
		account.deposit(amount);
		print(account.toString());
	}
	
	private BankAccount askForAccount(String question) {
		String accountNumberInput = getInputWithQuestion(question);
		int accountNumber;
		
		try {
			accountNumber = Integer.parseInt(accountNumberInput);
		} catch(NumberFormatException e) {
			hoppsan("'" + accountNumberInput + "' är inte ett giltigt kontonummer.");
			return null;
		}
		
		BankAccount account = bank.findByNumber(accountNumber);
		if(account == null) {
			hoppsan("Det finns inte något konto med kontonummer '" + accountNumber + "'");
			return null;
		}
		
		return account;
	}
	
	private void withdraw() {
		BankAccount account = askForAccount("från konto: ");
		if(account == null) {
			return;
		}
		
		String amountInput = getInputWithQuestion("belopp: ");
		double amount;
		
		try {
			amount = Double.parseDouble(amountInput);
		} catch(NumberFormatException e) {
			hoppsan("'" + amountInput + "' är inte ett giltigt belopp.");
			return;
		}
		
		if(account.getAmount() - amount < 0) {
			hoppsan("Det verkar inte finnas tillräckligt med pengar på kontot. Saldo: '" + account.getAmount() + "'");
			return;
		}
		
		account.withdraw(amount);
		print(account.toString());
	}
	
	private void getAccountsOfCustomerWithId() {
		String idInput = getInputWithQuestion("id: ");
		long idNumber;
		
		try {
			idNumber = Long.parseLong(idInput);
		} catch(NumberFormatException e) {
			hoppsan("'" + idInput + "' är inte ett giltigt id-nummer.");
			return;
		}
		
		ArrayList<BankAccount> accountsOfCustomer = bank.findAccountsForHolder(idNumber);
		
		if(accountsOfCustomer.size() == 0) {
			hoppsan("Det finns inga konton registrerade till kund med id '" + idNumber + "'");
			return;
		}
		
		for(BankAccount bankAccount : accountsOfCustomer) {
			print(bankAccount.toString());
		}
	}
	
	private void searchForCustomersByName() {
		String nameQuery = getInputWithQuestion("namn: ");
		ArrayList<Customer> matchingCustomers = bank.findByPartOfName(nameQuery);
		
		if(!matchingCustomers.isEmpty()) {
			for(Customer customer : matchingCustomers)  {
				print(customer.toString());
			}
		} else {
			hoppsan("Det finns inget konto som matchar sökningen '" + nameQuery + "'!");
		}
	}
	
	private void createAccount() {
		String holderName = getInputWithQuestion("namn: ");
		String idInput = getInputWithQuestion("id: ");
		long idNumber;
		
		try {
			idNumber = Long.parseLong(idInput);
		} catch(NumberFormatException e) {
			hoppsan("'" + idInput + "' är inte ett giltigt id-nummer.");
			return;
		}
		int accountNumber;
		
		try {
			accountNumber = bank.addAccount(holderName, idNumber);
		} catch(IllegalArgumentException e) {
			hoppsan("En annan kund verkar redan ha det id-nummret. Bara unika idnummer är giltiga.");
			return;
		}
		
		print("konto skapat: " + accountNumber);
	}
	
	private void printAccounts() {
		ArrayList<BankAccount> bankAccounts = bank.getAllAccounts();
		
		if(bankAccounts.isEmpty()) {
			hoppsan("Det finns inga konton än!");
			return;
		}
		
		for(BankAccount bankAccount : bankAccounts) {
			print(bankAccount.toString());
		}
	}
	
	private String getInputWithQuestion(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}
	
	private void printOptions() {
		String[] linesToPrint = {
			"1. Hitta konto utifrån innehavare",
			"2. Sök kontoinnehavare utifrån (del av) namn",
			"3. Sätt in",
			"4. Ta ut",
			"5. Överföring",
			"6. Skapa konto",
			"7. Ta bort konto",
			"8. Skriv ut konton",
			"9. Avsluta"
		};
		
		for(int i = 0; i < linesToPrint.length; i++) {
			print(linesToPrint[i]);
		}
	}
	
	private void print(String text) {
		System.out.println(text);
	}
	
	private void print() {
		System.out.println();
	}
	
	private void prettyPrint(String text) {
		String decoration = "#";
		
		print(decoration.repeat(text.length() + 6 + 2));
		print(decoration.repeat(3) + " " + text + " " + decoration.repeat(3));
		print(decoration.repeat(text.length() + 6 + 2));
		print();
	}
	
	private void hoppsan(String text) {
		print();
		print("Hoppsan!");
		print(text);
	}

	private boolean canHandleMainLoopInput(String input) {
		int inputInteger = 0;
		
		try {
			inputInteger = Integer.parseInt(input);
		} catch(NumberFormatException e) {
			return false;
		}
		
		if(inputInteger < 1 || inputInteger > 9) {
			return false;
		}
		
		return true;
	}
}
