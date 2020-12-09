import java.util.ArrayList;

public class Bank {
	private ArrayList<BankAccount> accountsInBank = new ArrayList<BankAccount>();
	private ArrayList<Customer> customersInBank = new ArrayList<Customer>();
	
	public int addAccount(String holderName, long idNr) throws IllegalArgumentException {
		Customer holder = findHolder(idNr);
		
		BankAccount newBankAccount;
		
		if(holder == null) {
			newBankAccount = new BankAccount(holderName, idNr);
			accountsInBank.add(newBankAccount);
			customersInBank.add(newBankAccount.getHolder());
		} else {
			/* Specen var att id-nummret kunde antagas vara unikt, men här
			 * kollar jag ändå ifall användaren råkar skriva samma nummer
			 * som en annan kund men med ett annat namn. */
			if(holder.getName().toLowerCase().compareTo(holderName.toLowerCase()) != 0) {
				throw new IllegalArgumentException("A customer with id '" + idNr + "' already exists but names don't match. ('" + holderName +  "' != '" + holder.getName() + "')");
			}
			
			newBankAccount = new BankAccount(holder);
			accountsInBank.add(newBankAccount);
		}
		
		return newBankAccount.getAccountNumber();
	}
	
	public Customer findHolder(long idNr) {
		for(Customer customer : customersInBank) {
			if(customer.getIdNr() == idNr) {
				return customer;
			}
		}
		
		return null;
	}
	
	public boolean removeAccount(int number) {
		int accountIndexToRemove = -1;
		
		for(int i = 0; i < accountsInBank.size(); i++) {
			if(accountsInBank.get(i).getAccountNumber() == number) {
				accountIndexToRemove = i;
				break;
			}
		}
		
		if(accountIndexToRemove >= 0) {
			accountsInBank.remove(accountIndexToRemove);
			return true;
		}
		
		return false;
	}
	
	public ArrayList<BankAccount> getAllAccounts() {
		ArrayList<BankAccount> accountsSortedByName = new ArrayList<BankAccount>();
		accountsSortedByName.addAll(accountsInBank);
		int size = accountsSortedByName.size();
		
		// Behöver inte sortera ifall det bara finns 1 eller inga kudner.
		if(size <= 1) {
			return accountsSortedByName;
		}
		
		/* Sortera efter kontoinnehavarens namn. Går igenom alla konton och jämför
		 * varje konto med alla andra konton. Ifall kontoinnehavarens namn är 'större'
		 * än den vars namn vi jämför med så bytar de plats. Kanske inte den mest
		 * effektiva algoritmen, men det funkar xP */
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				BankAccount accountA = accountsSortedByName.get(i);
				BankAccount accountB = accountsSortedByName.get(j);
				
				if(accountA.getHolder().getName().compareTo(accountB.getHolder().getName()) < 0) {
					accountsSortedByName.set(j, accountA);
					accountsSortedByName.set(i, accountB);
				}
			}
		}
		
		return accountsSortedByName;
	}
	
	public BankAccount findByNumber(int accountNumber) {
		for(BankAccount bankAccount : accountsInBank) {
			if(bankAccount.getAccountNumber() == accountNumber) {
				return bankAccount;
			}
		}
		
		return null;
	}
	
	public ArrayList<BankAccount> findAccountsForHolder(long idNr) {
		ArrayList<BankAccount> accountsForHolder = new ArrayList<BankAccount>();
		
		for(BankAccount bankAccount : accountsInBank) {
			if(bankAccount.getHolder().getIdNr() == idNr) {
				accountsForHolder.add(bankAccount);
			}
		}
		
		return accountsForHolder;
	}

	public ArrayList<Customer> findByPartOfName(String partOfName) {
		ArrayList<Customer> matchingCustomers = new ArrayList<Customer>();
		
		for(Customer customer : customersInBank) {
			/* Ifall kundens namn innehåller partOfName (case-insensitive)
			   så läggs den till i listan som returneras. */
			if(customer.getName().toLowerCase().contains(partOfName.toLowerCase())) {
				matchingCustomers.add(customer);
			}
		}
		
		return matchingCustomers;
	}
}
