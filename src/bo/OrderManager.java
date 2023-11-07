package bo;

import entities.Order;
import datastructures.LListSet;
import entities.Customer;
import entities.Product;

public class OrderManager {
	private final CustomerManager customers;
	private final ProductManager products;
	private final LListSet<Order> orders;

	public OrderManager(CustomerManager customers, ProductManager products, LListSet<Order> orders) {
		this.customers = customers;
		this.products = products;
		this.orders = orders;
	}

	public void buy(int customerId, int productId, int amount) throws Exception {
		Customer customer = customers.searchById(customerId);

		if (customer == null) {
			throw new IllegalArgumentException("No such customer");
		}
		
		Product product = products.searchById(customerId);

		if (product == null) {
			throw new IllegalArgumentException("No such product");
		}

		if (amount <= 0) {
			throw new IllegalArgumentException("Quantity to buy must be positive");
		}

		if (product.getQuantity() - amount < 0) {
			throw new IllegalArgumentException("Cannot buy more than available quantity");
		}

		orders.insert(new Order(customer, product, amount));

		product.setQuantity(product.getQuantity() - amount);
	}

	public LListSet<Order> getAll() {
		return orders;
	}
}
