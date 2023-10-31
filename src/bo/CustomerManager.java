package bo;

import datastructures.LListSet;
import datastructures.StorageBackend;
import entities.Customer;

public class CustomerManager {

    StorageBackend<Customer> store;

    public CustomerManager(StorageBackend<Customer> store) {
        this.store = store;
    }

    public void clear() {
	    store.clear();
    }

    public void add(Customer customer) throws IllegalArgumentException {
        if (store.exists(customer)) {
            throw new IllegalArgumentException("The customer id exists");
        }

        store.insert(customer);
    }

    public void removeById(int id) throws IllegalArgumentException {
        Customer product = searchById(id);

        if (product == null) {
            throw new IllegalArgumentException("The customer does not exist, can't delete");
        }

        store.delete(product);
    }

    public Customer searchById(int id) {
        Customer customerWithID = new Customer();
        customerWithID.setId(id);

        return store.find(customerWithID);
    }


    public LListSet<Customer> getAll() {
        LListSet<Customer> customers = new LListSet<>();

        for (Customer customer : store) {
            customers.insert(customer);
        }

        return customers;
    }
}
