/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author DELL
 */
public class Order implements Comparable<Order> {
	private Customer customer;
	private Product product;
	private int quantity;

	public Order(Customer customer, Product product, int quantity) {
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int compareTo(Order o) {
		int productComp = getProduct().compareTo(o.getProduct());
		if (productComp != 0) {
			return productComp;
		} else {
			return getCustomer().compareTo(o.getCustomer());
		}
	}
}
