
import bo.CustomerManager;
import bo.OrderManager;
import bo.ProductManager;
import datastructures.LListSet;
import datastructures.StorageBackend;
import entities.Customer;
import entities.Order;
import entities.Product;
import io.CustomerFile;
import io.ProductFile;
import java.util.Random;
import ui.Display;
import ui.Mode;
import utils.Inputter;

public class Main {

	public static void main(String[] args) {
		// This uses Java's polymorphism features. To change it to the linked list implementation
		// change this line to
		// StorageBackend<Product> store = new LListSet<>();
		StorageBackend<Product> storeProduct = new LListSet<>();
		StorageBackend<Customer> storeCustomer = new LListSet<>();
		LListSet<Order> storeOrder = new LListSet<>();

		ProductManager manager = new ProductManager(storeProduct);
		CustomerManager cManager = new CustomerManager(storeCustomer);
		OrderManager oManager = new OrderManager(cManager, manager, storeOrder);

		Display display = new Display(manager, cManager,  oManager);

		ProductFile file = new ProductFile("products.txt");
		CustomerFile cFile = new CustomerFile("customers.txt");

		while (true) {
			Mode mode = display.displayMenuAndReturnMode();

			switch (mode) {
				case ADD_PRODUCT:
					display.displayAddProduct();
					break;
				case SEARCH_PRODUCTS:
					display.displaySearchScreen();
					break;
				case ALL_PRODUCTS:
					display.displayAllProducts();
					break;
				case REMOVE_PRODUCT:
					display.displayDeleteProduct();
					break;
				case UPDATE_PRODUCT:
					display.displayUpdateProduct();
					break;
				case SORT_BY_ID:
					if (storeProduct instanceof  LListSet) {
						((LListSet<Product>) storeProduct).sort();
						display.displayAllProducts();
					} else {
						System.out.println("sort is only implemented on LList");
					}
					break;
				case TEST_INSERT_AFTER_K:
					if (storeProduct instanceof LListSet) {
						int insertAfter = Inputter.getInt("Enter place to insert at: ", (int _unused) -> {
						});

						int randId = new Random().nextInt(10000);
						((LListSet<Product>) storeProduct).insertAfterK(insertAfter, new Product(
						    randId, "Nice", 10, 10));
					} else {
						System.out.println("Feature not available: Backend store must be a linked list");
					}
					break;
				case READ_PRODUCTS:
					try {
						storeProduct.clear();
						int count = file.readFromFile(manager);
						System.out.println("Read " + count + " items from disk");
					} catch (Exception e) {
						System.out.printf("ERROR: %s\n", e.getMessage());
					}
					break;
				case WRITE_PRODUCTS:
					try {
						file.writeToFile(manager);
						System.out.println("File written");
					} catch (Exception e) {
						System.out.printf("ERROR: %s\n", e.getMessage());
					}
					break;
				case LOAD_CUSTOMER_FROM_FILE:
					try {
						storeCustomer.clear();
						int count = cFile.readFromFile(cManager);
						System.out.println("Read " + count + " items from disk");
					} catch (Exception e) {
						System.out.printf("ERROR: %s\n", e.getMessage());
					}
					break;
				case SAVE_CUSTOMER_TO_FILE:
					try {
						cFile.writeToFile(cManager);
						System.out.println("File written");
					} catch (Exception e) {
						System.out.printf("ERROR: %s\n", e.getMessage());
					}
					break;
				case ADD_NEW_CUSTOMER:
					display.addNewCustomer();
					break;
				case FIND_CUSTOMER_BY_ID:
					display.displayFindCustomer();
					break;
				case DELETE_CUSTOMER_BY_ID:
					display.displayDeleteCustomer();
					break;
				case ALL_CUSTOMERS:
					display.displayAllCustomers();
					break;
				case ALL_ORDERS:
					display.displayOrders();
					break;
				case SORT_ORDERS:
					storeOrder.sort();
					break;
				case BUY_STUFF:
					display.displayBuying();
					break;
				case EXIT:
					System.out.println("Good bye");
					return;
			}
		}
	}
}
