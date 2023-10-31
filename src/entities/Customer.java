package entities;

public class Customer implements Comparable<Customer> {

	private int id;
	private String name;
	private String phone;

	public Customer() {
	}

	public Customer(int id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("The id should be a positive integer");
		}
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int compareTo(Customer o) {
		return Integer.compare(this.getId(), o.getId());
	}

	@Override
	public String toString() {
		return String.format("%d,%s,%s", getId(), getName(), getPhone());
	}
}
