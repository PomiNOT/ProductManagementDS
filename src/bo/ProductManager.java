package bo;

import datastructures.LListSet;
import datastructures.StorageBackend;
import entities.Product;

public class ProductManager {

    // See, it does not depend on any particular implementation
    // It just wants an object that implements the interface it expects
    StorageBackend<Product> store;

    public ProductManager(StorageBackend<Product> store) {
        this.store = store;
    }

    public void clear() {
	    store.clear();
    }

    public void add(Product product) throws IllegalArgumentException {
        if (store.exists(product)) {
            throw new IllegalArgumentException("The product id exists");
        }

        store.insert(product);
    }

    public void removeById(int id) throws IllegalArgumentException {
        Product product = searchById(id);

        if (product == null) {
            throw new IllegalArgumentException("The product does not exist, can't delete");
        }

        store.delete(product);
    }

    public Product searchById(int id) {
        Product productWithId = new Product();
        productWithId.setId(id);

        return store.find(productWithId);
    }

    public LListSet<Product> searchByName(String query) {
        LListSet<Product> searchResults = new LListSet<>();

        // In the case of Linked List implementation, this will do a linear walk
        // In the case of BST implementation, this will do a breadth first search
        // However, the user does not need to care about any of that.
        // He can just iterate over the underlying data structure like an array
        // because it implements the Iterable interface
        for (Product product : store) {
            String productNameLower = product.getName().toLowerCase();
            if (productNameLower.contains(query.toLowerCase())) {
                searchResults.insert(product);
            }
        }

        return searchResults;
    }

    public LListSet<Product> getAll() {
        LListSet<Product> products = new LListSet<>();

        for (Product product : store) {
            products.insert(product);
        }

        return products;
    }
}
