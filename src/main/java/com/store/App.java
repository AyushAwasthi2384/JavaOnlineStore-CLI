package com.store;

import com.store.models.Customer;
import com.store.models.Product;
import com.store.models.Order;
import com.store.models.OrderItem;

import com.store.services.CustomerService;
import com.store.services.ProductService;
import com.store.services.OrderService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add a New Product");
            System.out.println("2. Update Product Stock");
            System.out.println("3. Add a New Customer");
            System.out.println("4. Update Customer Details");
            System.out.println("5. Delete a Customer");
            System.out.println("6. Place an Order");
            System.out.println("7. Fetch Products");
            System.out.println("8. Fetch Customer Orders");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a new product
                    Product newProduct = createProduct(scanner);
                    try {
                        productService.addProduct(newProduct);
                        System.out.println("Product added successfully. New product ID: " + newProduct.getProductId());
                    } catch (SQLException e) {
                        System.out.println("Failed to add product: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Update product stock
                    updateProductStock(scanner, productService);
                    break;

                case 3:
                    // Add a new customer
                    Customer newCustomer = createCustomer(scanner);
                    try {
                        customerService.addCustomer(newCustomer);
                        System.out.println(
                                "Customer added successfully. New customer ID: " + newCustomer.getCustomerId());
                    } catch (SQLException e) {
                        System.out.println("Failed to add customer: " + e.getMessage());
                    }
                    break;

                case 4:
                    // Update customer details
                    updateCustomerDetails(scanner, customerService);
                    break;

                case 5:
                    // Delete a customer
                    deleteCustomer(scanner, customerService);
                    break;

                case 6:
                    // Place an order
                    placeOrder(scanner, orderService, productService);
                    break;

                case 7:
                    try {
                        List<Product> products = productService.getAllProducts();
                        System.out.println("Products:");
                        for (Product product : products) {
                            System.out.printf("ID: %d, Name: %s, Category: %s, Price: %.2f, Stock: %d%n",
                                    product.getProductId(), product.getName(), product.getCategory(),
                                    product.getPrice(), product.getStockQuantity());
                        }
                    } catch (SQLException e) {
                        System.out.println("Failed to fetch products: " + e.getMessage());
                    }
                    break;

                case 8:
                    // Fetch Customer Orders
                    fetchCustomerOrders(scanner, orderService);
                    break;

                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Product createProduct(Scanner scanner) {
        Product product = new Product();
        System.out.print("Enter product name: ");
        product.setName(scanner.nextLine());
        System.out.print("Enter product category: ");
        product.setCategory(scanner.nextLine());
        System.out.print("Enter product price: ");
        product.setPrice(scanner.nextDouble());
        System.out.print("Enter stock quantity: ");
        product.setStockQuantity(scanner.nextInt());
        scanner.nextLine(); // Consume newline
        return product;
    }

    private static void updateProductStock(Scanner scanner, ProductService productService) {
        System.out.print("Enter product ID to update: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new stock quantity: ");
        int newQuantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            productService.updateProductStock(productId, newQuantity);
            System.out.println("Product stock updated successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to update product stock: " + e.getMessage());
        }
    }

    private static Customer createCustomer(Scanner scanner) {
        Customer customer = new Customer();
        System.out.print("Enter customer name: ");
        customer.setName(scanner.nextLine());
        System.out.print("Enter customer email: ");
        customer.setEmail(scanner.nextLine());
        System.out.print("Enter customer phone: ");
        customer.setPhone(scanner.nextLine());
        return customer;
    }

    private static void updateCustomerDetails(Scanner scanner, CustomerService customerService) {
        System.out.print("Enter customer ID to update: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            Customer customer = customerService.getCustomerById(customerId);
            System.out.print("Enter new name (current: " + customer.getName() + "): ");
            customer.setName(scanner.nextLine());
            System.out.print("Enter new email (current: " + customer.getEmail() + "): ");
            customer.setEmail(scanner.nextLine());
            System.out.print("Enter new phone (current: " + customer.getPhone() + "): ");
            customer.setPhone(scanner.nextLine());
            customerService.updateCustomer(customer);
            System.out.println("Customer details updated successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to update customer: " + e.getMessage());
        }
    }

    private static void deleteCustomer(Scanner scanner, CustomerService customerService) {
        System.out.print("Enter customer ID to delete: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            customerService.deleteCustomer(customerId);
            System.out.println("Customer deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to delete customer: " + e.getMessage());
        }
    }

    private static void placeOrder(Scanner scanner, OrderService orderService, ProductService productService) {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        List<OrderItem> orderItems = new ArrayList<>();

        while (true) {
            System.out.print("Enter product ID to order (or -1 to finish): ");
            int productId = scanner.nextInt();
            if (productId == -1)
                break;

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Here you can check stock before placing the order
            try {
                Product product = productService.getProductById(productId);
                if (product.getStockQuantity() < quantity) {
                    System.out.println("Insufficient stock for product: " + product.getName());
                    continue;
                }

                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(productId);
                orderItem.setQuantity(quantity);
                orderItems.add(orderItem);
            } catch (SQLException e) {
                System.out.println("Failed to fetch product: " + e.getMessage());
            }
        }

        try {
            Order order = new Order();
            order.setCustomerId(customerId);
            order.setOrderDate(new Date());
            order.setOrderItems(orderItems);
            orderService.placeOrder(order);
            System.out.println("Order placed successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to place order: " + e.getMessage());
        }
    }

    private static void fetchCustomerOrders(Scanner scanner, OrderService orderService) {
        System.out.print("Enter customer ID to fetch orders: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            List<Order> orders = orderService.getOrdersByCustomerId(customerId);
            if (orders.isEmpty()) {
                System.out.println("No orders found for customer ID: " + customerId);
            } else {
                System.out.println("Orders for customer ID " + customerId + ":");
                for (Order order : orders) {
                    System.out.println("Order ID: " + order.getOrderId() + ", Date: " + order.getOrderDate());
                    for (OrderItem item : order.getOrderItems()) {
                        System.out
                                .println("\tProduct ID: " + item.getProductId() + ", Quantity: " + item.getQuantity());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch orders: " + e.getMessage());
        }
    }

}
