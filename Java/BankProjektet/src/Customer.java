
public class Customer {
	private String name;
	private long idNumber;
	private int customerNumber;
	
	private static int internalCustomerNumberCounter = 1000;
	
	private int getNewCustomerNumber() {
		internalCustomerNumberCounter++;
		return internalCustomerNumberCounter;
	}
	
	public Customer(String name, long idNumber) {
		this.name = name;
		this.idNumber = idNumber;
		this.customerNumber = getNewCustomerNumber();
	}
	
	public String getName() {
		return name;
	}
	
	public long getIdNr() {
		return idNumber;
	}
	
	public int getCustomerNumber() {
		return customerNumber;
	}
	
	public String toString() {
		return name + ", id " + idNumber + " kundnr " + customerNumber;
	}
}
