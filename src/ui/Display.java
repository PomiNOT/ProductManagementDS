package ui;

import bo.CustomerManager;
import bo.OrderManager;
import bo.ProductManager;
import datastructures.LListSet;
import entities.Customer;
import entities.Order;
import entities.Product;
import utils.Inputter;

public class Display {

	private final ProductManager productManager;
	private final CustomerManager customerManager;
	private final OrderManager orderManager;

	public Display(ProductManager productManager, CustomerManager customerManager, OrderManager orderManager) {
		this.productManager = productManager;
		this.customerManager = customerManager;
		this.orderManager = orderManager;
	}

	public Mode displayMenuAndReturnMode() {
		Mode[] modes = Mode.values();

		for (int i = 0; i < modes.length; i++) {
			System.out.printf("%d. %s\n", i + 1, modes[i]);
		}

		int choice = Inputter.getInt("Enter an option: ", (int opt) -> {
			if (opt < 1 || opt > modes.length) {
				throw new IllegalArgumentException("Choice must be within range 1 to " + modes.length);
			}
		});

		return modes[choice - 1];
	}

	public void displayAddProduct() {
		Product product = new Product();
		Inputter.getInt("Enter product id: ", product::setId);
		Inputter.getString("Enter product name: ", product::setName);
		Inputter.getInt("Enter product price: ", product::setPrice);
		Inputter.getInt("Enter product quantity: ", product::setQuantity);

		try {
			productManager.add(product);
			System.out.println("Added product");
		} catch (IllegalArgumentException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public void displayAllProducts() {
		LListSet<Product> products = productManager.getAll();

		if (products.isEmpty()) {
			System.out.println("There are no products");
			return;
		}

		displayProductsList(products);
	}

	public void displayDeleteProduct() {
		int id = Inputter.getInt("Enter product id: ", (int enteredId) -> {
			if (enteredId <= 0) {
				throw new IllegalArgumentException("The id should be a positive integer");
			}
		});

		try {
			productManager.removeById(id);
			System.out.println("Removed product id " + id);
		} catch (IllegalArgumentException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public void displayUpdateProduct() {
		int id = Inputter.getInt("Enter product id to update: ", (int enteredId) -> {
			if (enteredId <= 0) {
				throw new IllegalArgumentException("The id should be a positive integer");
			}
		});

		Product product = productManager.searchById(id);
		if (product == null) {
			System.out.println("ERROR: The product cannot be found");
			return;
		}

		Inputter.getString("Enter new product name: ", product::setName);
		Inputter.getInt("Enter new product price: ", product::setPrice);
		Inputter.getInt("Enter new product quantity: ", product::setQuantity);

		System.out.println("Updated product");
	}

	public void displaySearchScreen() {
		String query = Inputter.getString("Enter search term: ");
		LListSet<Product> searchResults = productManager.searchByName(query);

		if (searchResults.isEmpty()) {
			System.out.println("No items matched your query");
			return;
		}

		System.out.printf("Search results (%d items):\n", searchResults.getCount());
		displayProductsList(searchResults);
	}

	private static void displayProductsList(LListSet<Product> products) {
		TableView tableView = new TableView(new String[]{"ID", "Name", "Quantity", "Price"});

		for (Product product : products) {
			tableView.addRow(new String[]{
				Integer.toString(product.getId()),
				product.getName(),
				Integer.toString(product.getQuantity()),
				Integer.toString(product.getPrice()),});
		}

		tableView.display();
	}

	public void addNewCustomer() {
		Customer customer = new Customer();
		Inputter.getInt("Enter customer id: ", customer::setId);
		Inputter.getString("Enter customer name: ", customer::setName);
		Inputter.getString("Enter customer phone: ", (String phone) -> {
			if (!phone.matches("[0-9]{10,}")) {
				throw new IllegalArgumentException("Phone number must only contain digits and be at least 10 digits long");
			}

			customer.setPhone(phone);
		});

		try {
			customerManager.add(customer);
			System.out.println("Added customer");
		} catch (IllegalArgumentException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public void displayAllCustomers() {
		LListSet<Customer> customers = customerManager.getAll();

		if (customers.isEmpty()) {
			System.out.println("There are no customers");
			return;
		}

		displayCustomersList(customers);
	}

	public void displayDeleteCustomer() {
		int id = Inputter.getInt("Enter customer id: ", (int enteredId) -> {
			if (enteredId <= 0) {
				throw new IllegalArgumentException("The id should be a positive integer");
			}
		});

		try {
			customerManager.removeById(id);
			System.out.println("Removed customer id " + id);
		} catch (IllegalArgumentException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public void displayFindCustomer() {
		int id = Inputter.getInt("Enter customer id: ", (int enteredId) -> {
			if (enteredId <= 0) {
				throw new IllegalArgumentException("The id should be a positive integer");
			}
		});

		Customer found = customerManager.searchById(id);
		if (found == null) {
			System.out.println("The customer cannot be found");
		} else {
			LListSet<Customer> results = new LListSet<>();
			results.insert(found);
			displayCustomersList(results);
		}
	}

	private static void displayCustomersList(LListSet<Customer> customers) {
		TableView tableView = new TableView(new String[]{"ID", "Name", "Phone"});

		for (Customer customer : customers) {
			tableView.addRow(new String[]{
				Integer.toString(customer.getId()),
				customer.getName(),
				customer.getPhone()
			});
		}

		tableView.display();
	}

	public void displayBuying() {
		int ccode = Inputter.getInt("Enter customer id: ");
		int pcode = Inputter.getInt("Enter product id: ");
		int amount = Inputter.getInt("Enter amount: ");

		try {
			orderManager.buy(ccode, pcode, amount);
			System.out.println("Bought product");
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public void displayOrders() {
		if (orderManager.getAll().isEmpty()) {
			System.out.println("No orders");
			return;
		}

		TableView tableView = new TableView(new String[]{"Product ID", "Customer ID", "Amount"});

		for (Order order : orderManager.getAll()) {
			tableView.addRow(new String[]{
				Integer.toString(order.getProduct().getId()),
				Integer.toString(order.getCustomer().getId()),
				Integer.toString(order.getQuantity())
			});
		}

		tableView.display();
	}
}
