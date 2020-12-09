
public class Customer {
	private String name;
	private long idNumber;
	private int customerNumber;
	
	public Customer(String name, long idNumber) {
		this.name = name;
		this.idNumber = idNumber;
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
