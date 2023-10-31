package ui;

public enum Mode {
    ADD_PRODUCT("Add product"),
    REMOVE_PRODUCT("Remove product"),
    SEARCH_PRODUCTS("Search products"),
    ALL_PRODUCTS("View all products"),
    SORT_BY_ID("Sort by id"),
    UPDATE_PRODUCT("Update product"),
    READ_PRODUCTS("Read products from file"),
    WRITE_PRODUCTS("Write products to file"),
    TEST_INSERT_AFTER_K("Test insert after k"),
    LOAD_CUSTOMER_FROM_FILE("Load customers from file"),
    SAVE_CUSTOMER_TO_FILE("Save customers to file"),
    ADD_NEW_CUSTOMER("Add new customer"),
    FIND_CUSTOMER_BY_ID("Find customer by id"),
    DELETE_CUSTOMER_BY_ID("Delete customer by id"),
    ALL_CUSTOMERS("View all customers"),
    EXIT("Exit");

    private  final  String label;
    private Mode(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
