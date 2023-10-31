package entities;

// this class manages a product, it implements comparable
// the comparison subjects are the ids
public class Product implements Comparable<Product> {

	private int id;
	private String name;
	private int price;
	private int quantity;

	public Product(int id, String name, int quantity, int price) {
		this.name = name;
		this.price = price;
		this.id = id;
		this.quantity = quantity;
	}

	public Product() {

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
		if (name.isEmpty()) {
			throw new IllegalArgumentException("Name should not be empty");
		}
		this.name = normalizeText(name);
	}

	private String normalizeText(String original) {
		String[] words = original.trim().split("\\s+");
		StringBuilder builder = new StringBuilder();

		for (String word : words) {
			String firstChar = word.substring(0, 1);
			String theRest = word.substring(1).toLowerCase();
			builder.append(firstChar.toUpperCase()).append(theRest).append(" ");
		}

		return builder.toString().trim();
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		if (price < 0) {
			throw new IllegalArgumentException("The price should be an integer greater than or equal 0 (Free)");
		}
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("The quantity should be an integer greater than 0");
		}
		this.quantity = quantity;
	}

	@Override
	public int compareTo(Product o) {
		return Integer.compare(this.getId(), o.getId());
	}

	@Override
	public String toString() {
		return String.format("%d,%s,%d,%d", getId(), getName(), getPrice(), getQuantity());
	}
}
