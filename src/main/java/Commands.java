public interface Commands {
    String EXIT = "0";
    String ADD_CATEGORY = "1";
    String EDIT_CATEGORY_BY_ID = "2";
    String DELETE_CATEGORY_BY_ID = "3";
    String ADD_PRODUCT = "4";
    String EDIT_PRODUCT_BY_ID = "5";
    String DELETE_PRODUCT_BY_ID = "6";
    String PRINT_SUM_OF_PRODUCTS = "7";
    String PRINT_MAX_OF_PRICE_PRODUCT = "8";
    String PRINT_MIN_OF_PRICE_PRODUCT = "9";
    String PRINT_AVG_OF_PRICE_PRODUCT = "10";
    String PRINT_ALL_CATEGORIES = "11";
    String PRINT_ALL_PRODUCTS = "12";

    static void printCommands() {
        System.out.println("Please input " + EXIT + " for exit");
        System.out.println("Please input " + ADD_CATEGORY + " for add category");
        System.out.println("Please input " + EDIT_CATEGORY_BY_ID + " for edit category");
        System.out.println("Please input " + DELETE_CATEGORY_BY_ID + " for delete category");
        System.out.println("Please input " + ADD_PRODUCT + " for add product");
        System.out.println("Please input " + EDIT_PRODUCT_BY_ID + " for edit product");
        System.out.println("Please input " + DELETE_PRODUCT_BY_ID + " for delete product");
        System.out.println("Please input " + PRINT_SUM_OF_PRODUCTS + " for view sum of products");
        System.out.println("Please input " + PRINT_MAX_OF_PRICE_PRODUCT + " for view max of price  product");
        System.out.println("Please input " + PRINT_MIN_OF_PRICE_PRODUCT + " for view min of price  product");
        System.out.println("Please input " + PRINT_AVG_OF_PRICE_PRODUCT + " for view average price of products");
        System.out.println("PLease input " + PRINT_ALL_CATEGORIES + " for view all categories");
        System.out.println("PLease input " + PRINT_ALL_PRODUCTS + " for view all products");
    }


}
