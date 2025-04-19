import java.util.List;
import java.util.Scanner;

public class Program {
    private static ProductDAO productDAO;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide database connection URL as command line argument");
            System.out.println("Example: java Program \"jdbc:mysql://localhost:3306/?user=root&password=password\"");
            return;
        }

        productDAO = new ProductDAO(args[0]);

        while (true) {
            displayMenu();
            int choice = getUserChoice();
            executeFunction(choice);
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Product Management System ===");
        System.out.println("1. Read product list");
        System.out.println("2. Read a product by ID");
        System.out.println("3. Add a new product");
        System.out.println("4. Update a product");
        System.out.println("5. Delete a product");
        System.out.println("6. Exit");
        System.out.print("Enter your choice (1-6): ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void executeFunction(int choice) {
        switch (choice) {
            case 1 -> readAllProducts();
            case 2 -> readProductById();
            case 3 -> addProduct();
            case 4 -> updateProduct();
            case 5 -> deleteProduct();
            case 6 -> {
                System.out.println("Exiting program...");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice! Please select 1-6.");
        }
    }

    private static void readAllProducts() {
        List<Product> products = productDAO.readAll();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            products.forEach(System.out::println);
        }
    }

    private static void readProductById() {
        System.out.print("Enter product ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Product product = productDAO.read(id);
        System.out.println(product != null ? product : "Product not found.");
    }

    private static void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        Product product = new Product(name, price, quantity);
        Integer id = productDAO.add(product);
        System.out.println("Product added with ID: " + id);
    }

    private static void updateProduct() {
        System.out.print("Enter product ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Product product = productDAO.read(id);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter new name (current: " + product.getName() + "): ");
        String name = scanner.nextLine();
        System.out.print("Enter new price (current: " + product.getPrice() + "): ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter new quantity (current: " + product.getQuantity() + "): ");
        int quantity = Integer.parseInt(scanner.nextLine());

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        boolean success = productDAO.update(product);
        System.out.println(success ? "Product updated successfully" : "Update failed");
    }

    private static void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean success = productDAO.delete(id);
        System.out.println(success ? "Product deleted successfully" : "Delete failed");
    }
}