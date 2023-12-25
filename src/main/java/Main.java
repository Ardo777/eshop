import lombok.Builder;
import managers.CategoryManager;
import managers.ProductManager;
import model.Category;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class Main implements Commands {
    private static Scanner scanner = new Scanner(System.in);
    private static CategoryManager categoryManager = new CategoryManager();
    private static ProductManager productManager = new ProductManager();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            switch (scanner.nextLine()) {
                case "0":
                    isRun = false;
                    break;
                case "1":
                    addCategory();
                    break;
                case "2":
                    editCategory();
                    break;
                case "3":
                    deleteCategory();
                    break;
                case "4":
                    addProduct();
                    break;
                case "5":
                    editProduct();
                    break;
                case "6":
                    deleteProduct();
                    break;
                case "7":
                    System.out.println("Quantity of all products" + productManager.sumProducts());
                    break;
                case "8":
                    System.out.println("Price of the most expensive product--> " + productManager.maxPriceOfProducts());
                    break;
                case "9":
                    System.out.println("Price of the most cheapest product--> " + productManager.minPriceOfProducts());
                    break;
                case "10":
                    System.out.println("Average sum products-->" + productManager.avgPriceOfProducts());
                    break;
                case "11":
                    printAllCategories();
                    break;
                case "12":
                    printAllProducts();
                    break;
            }
        }
    }

    private static void printAllCategories() {
        List<Category> allCategories = categoryManager.getAllCategories();
        for (Category allCategory : allCategories) {
            System.out.println(allCategory);
        }

    }

    private static void printAllProducts() {
        List<Product> allProducts = productManager.getAllProducts();
        for (Product allProduct : allProducts) {
            System.out.println(allProduct);
        }
    }

    private static void deleteProduct() {
        printAllProducts();
        System.out.println("Please input product id for delete");
        String id = scanner.nextLine();
        if (!id.chars().allMatch(Character::isDigit)) {
            System.out.println("Please input only number!");
        }
        productManager.deleteProductById(Integer.parseInt(id));
        System.out.println("Product deleted");
    }

    private static void editProduct() {
        System.out.println("Please input product (name,description,price,quantity) for edit");
        String[] arrayOfStr = scanner.nextLine().split(",");

        if (arrayOfStr.length != 4) {
            System.out.println("Please fill out all fields");
            return;
        }
        printAllCategories();
        System.out.println("Please input category id(only number)");
        String id = scanner.nextLine();
        if (!id.chars().allMatch(Character::isDigit)) {
            System.out.println("Please input only number");
            return;
        }
        if (arrayOfStr[0].trim().isEmpty() || arrayOfStr[1].trim().isEmpty()) {
            System.out.println("this states can't be empty!");
            return;
        }
        Product product = Product.builder()
                .name(arrayOfStr[0])
                .description(arrayOfStr[1])
                .price(Double.parseDouble(arrayOfStr[2]))
                .count(Integer.parseInt(arrayOfStr[3]))
                .category(categoryManager.getCategoryById(Integer.parseInt(id)))
                .build();
        productManager.editProduct(product);
        System.out.println("Product updated");

    }

    private static void addProduct() {
        System.out.println("write the product (name,description,price,quantity) for add");
        String[] arrayOfStr = scanner.nextLine().split(",");

        if (arrayOfStr.length != 4) {
            System.out.println("you made a mistake, you need to fill in all the fields");
            return;
        }
        printAllCategories();
        System.out.println("Please input category id(only number)");
        String id = scanner.nextLine();
        if (!id.chars().allMatch(Character::isDigit)) {
            System.out.println("Please input only number");
            return;
        }
        if (arrayOfStr[0].trim().isEmpty() || arrayOfStr[1].trim().isEmpty()) {
            System.out.println("this states can't be empty!");
            return;
        }
        Product product = Product.builder()
                .name(arrayOfStr[0])
                .description(arrayOfStr[1])
                .price(Double.parseDouble(arrayOfStr[2]))
                .count(Integer.parseInt(arrayOfStr[3]))
                .category(categoryManager.getCategoryById(Integer.parseInt(id)))
                .build();
        productManager.add(product);
        System.out.println("Product added");
    }

    private static void deleteCategory() {
        printAllCategories();
        System.out.println("Please input category id for delete");
        String id = scanner.nextLine();
        if (!id.chars().allMatch(Character::isDigit)) {
            System.out.println("Please input only number!");
        }
        categoryManager.deleteCategoryById(Integer.parseInt(id));
        System.out.println("Category deleted");
    }

    private static void editCategory() {
        printAllCategories();
        System.out.println("Please input category id for change");
        String id = scanner.nextLine();
        if (id.chars().allMatch(Character::isDigit)) {
            System.out.println("Please input category name");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Please input name!");
            }
            Category category = Category.builder()
                    .id(Integer.parseInt(id))
                    .name(name)
                    .build();
            categoryManager.editCategoryById(category);
            System.out.println("Category updated");
        }
    }

    private static void addCategory() {
        System.out.println("Please input category for the products");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("Please input name!");
        }
        Category category = Category.builder()
                .name(name)
                .build();
        categoryManager.add(category);
        System.out.println("Category added");
    }


}

