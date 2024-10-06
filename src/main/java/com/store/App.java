// // package com.store;

// // import com.store.models.Product;
// // import com.store.services.ProductService;
// // import com.store.services.OrderService;
// // import com.store.models.Order;
// // import com.store.models.OrderItem;

// // import java.sql.SQLException;
// // import java.util.ArrayList;
// // import java.util.Date;
// // import java.util.List;

// // public class App {
// //     public static void main(String[] args) {
// //         ProductService productService = new ProductService();
// //         OrderService orderService = new OrderService();

// //         // 1. Add a new product
// //         Product newProduct = new Product();
// //         newProduct.setName("T-shirt");
// //         newProduct.setCategory("Clothing");
// //         newProduct.setPrice(19.99);
// //         newProduct.setStockQuantity(100);

// //         try {
// //             productService.addProduct(newProduct);
// //             System.out.println("Product added successfully.");
// //             System.out.println("New product ID: " + newProduct.getProductId()); // Check the generated product ID

// //             // 2. Update product stock
// //             productService.updateProductStock(newProduct.getProductId(), 120);
// //             System.out.println("Stock updated successfully.");

// //             // 3. Place an order
// //             Order newOrder = new Order();
// //             newOrder.setCustomerId(1);  // Assuming you have a customer with ID 1
// //             newOrder.setOrderDate(new Date());

// //             List<OrderItem> orderItems = new ArrayList<>();
// //             OrderItem item = new OrderItem();
// //             item.setProductId(newProduct.getProductId()); // Ensure this is set after adding the product
// //             item.setQuantity(2);
// //             item.setPrice(newProduct.getPrice());
// //             orderItems.add(item);

// //             // Validate OrderItems before placing the order
// //             if (validateOrderItems(orderItems)) {
// //                 orderService.placeOrder(newOrder, orderItems);
// //                 System.out.println("Order placed successfully.");
// //             } else {
// //                 System.err.println("Order validation failed. Please check the product IDs.");
// //             }

// //         } catch (SQLException e) {
// //             e.printStackTrace();
// //         }
// //     }

// //     private static boolean validateOrderItems(List<OrderItem> orderItems) {
// //         for (OrderItem item : orderItems) {
// //             if (item.getProductId() <= 0) {
// //                 System.err.println("Invalid product ID: " + item.getProductId());
// //                 return false;
// //             }
// //         }
// //         return true;
// //     }
// // }

// // package com.store;

// // import com.store.models.Product;
// // import com.store.services.ProductService;
// // import com.store.services.OrderService;
// // import com.store.models.Order;
// // import com.store.models.OrderItem;

// // import java.sql.SQLException;
// // import java.util.ArrayList;
// // import java.util.Date;
// // import java.util.List;
// // import java.util.Scanner;

// // public class App {
// //     public static void main(String[] args) {
// //         ProductService productService = new ProductService();
// //         OrderService orderService = new OrderService();
// //         Scanner scanner = new Scanner(System.in);

// //         try {
// //             // 1. Add a new product
// //             Product newProduct = new Product();
// //             System.out.print("Enter product name: ");
// //             newProduct.setName(scanner.nextLine());
// //             System.out.print("Enter product category: ");
// //             newProduct.setCategory(scanner.nextLine());
// //             System.out.print("Enter product price: ");
// //             newProduct.setPrice(scanner.nextDouble());
// //             System.out.print("Enter stock quantity: ");
// //             newProduct.setStockQuantity(scanner.nextInt());

// //             productService.addProduct(newProduct);
// //             System.out.println("Product added successfully. New product ID: " + newProduct.getProductId());

// //             // 2. Update product stock
// //             System.out.print("Enter new stock quantity: ");
// //             int newQuantity = scanner.nextInt();
// //             productService.updateProductStock(newProduct.getProductId(), newQuantity);
// //             System.out.println("Stock updated successfully.");

// //             // 3. Place an order
// //             Order newOrder = new Order();
// //             System.out.print("Enter customer ID: ");
// //             newOrder.setCustomerId(scanner.nextInt());  // Assuming you have customer IDs available
// //             newOrder.setOrderDate(new Date());

// //             List<OrderItem> orderItems = new ArrayList<>();
// //             OrderItem item = new OrderItem();
// //             item.setProductId(newProduct.getProductId());
// //             System.out.print("Enter quantity for the product: ");
// //             item.setQuantity(scanner.nextInt());
// //             item.setPrice(newProduct.getPrice());
// //             orderItems.add(item);

// //             orderService.placeOrder(newOrder, orderItems);
// //             System.out.println("Order placed successfully.");

// //         } catch (SQLException e) {
// //             e.printStackTrace();
// //         } finally {
// //             scanner.close();  // Close the scanner to prevent resource leaks
// //         }
// //     }
// // }

// package com.store;

// import com.store.models.Product;
// import com.store.services.ProductService;
// import com.store.services.OrderService;
// import com.store.models.Order;
// import com.store.models.OrderItem;

// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
// import java.util.Scanner;

// public class App {
//     public static void main(String[] args) {
//         ProductService productService = new ProductService();
//         OrderService orderService = new OrderService();
//         Scanner scanner = new Scanner(System.in);

//         while (true) {
//             System.out.println("Choose an option:");
//             System.out.println("1. Add a New Product");
//             System.out.println("2. Update Product Stock");
//             System.out.println("3. Place an Order");
//             System.out.println("4. Fetch Customer Orders");
//             System.out.println("5. Exit");
//             System.out.print("Enter your choice: ");

//             int choice = scanner.nextInt();
//             scanner.nextLine(); // Consume newline

//             switch (choice) {
//                 case 1:
//                     // Add a new product
//                     System.out.print("Enter product name: ");
//                     String name = scanner.nextLine();
//                     System.out.print("Enter product category: ");
//                     String category = scanner.nextLine();
//                     System.out.print("Enter product price: ");
//                     double price = scanner.nextDouble();
//                     System.out.print("Enter stock quantity: ");
//                     int stockQuantity = scanner.nextInt();
//                     scanner.nextLine(); // Consume newline

//                     Product newProduct = new Product();
//                     newProduct.setName(name);
//                     newProduct.setCategory(category);
//                     newProduct.setPrice(price);
//                     newProduct.setStockQuantity(stockQuantity);

//                     try {
//                         productService.addProduct(newProduct);
//                         System.out.println("Product added successfully. New product ID: " + newProduct.getProductId());
//                     } catch (SQLException e) {
//                         System.out.println("Failed to add product: " + e.getMessage());
//                     }
//                     break;

//                 case 2:
//                     // Update product stock
//                     System.out.print("Enter product ID to update: ");
//                     int productId = scanner.nextInt();
//                     System.out.print("Enter new stock quantity: ");
//                     int newQuantity = scanner.nextInt();

//                     try {
//                         productService.updateProductStock(productId, newQuantity);
//                         System.out.println("Stock updated successfully.");
//                     } catch (SQLException e) {
//                         System.out.println("Failed to update stock: " + e.getMessage());
//                     }
//                     break;

//                 case 3:
//                     // Place an order
//                     System.out.print("Enter customer ID: ");
//                     int customerId = scanner.nextInt();
//                     Order newOrder = new Order();
//                     newOrder.setCustomerId(customerId);
//                     newOrder.setOrderDate(new Date());

//                     List<OrderItem> orderItems = new ArrayList<>();
//                     while (true) {
//                         System.out.print("Enter product ID for order item (0 to finish): ");
//                         int itemProductId = scanner.nextInt();
//                         if (itemProductId == 0) {
//                             break;
//                         }
//                         System.out.print("Enter quantity: ");
//                         int quantity = scanner.nextInt();
//                         System.out.print("Enter price: ");
//                         double orderPrice = scanner.nextDouble();

//                         OrderItem item = new OrderItem();
//                         item.setProductId(itemProductId);
//                         item.setQuantity(quantity);
//                         item.setPrice(orderPrice);
//                         orderItems.add(item);
//                     }

//                     try {
//                         orderService.placeOrder(newOrder, orderItems);
//                         System.out.println("Order placed successfully.");
//                     } catch (SQLException e) {
//                         System.out.println("Failed to place order: " + e.getMessage());
//                     }
//                     break;

//                 case 4:
//                     // Fetch Customer Orders (implement as needed)
//                     System.out.println("Fetching customer orders is not yet implemented.");
//                     break;

//                 case 5:
//                     System.out.println("Exiting...");
//                     scanner.close();
//                     return;

//                 default:
//                     System.out.println("Invalid choice. Please try again.");
//             }
//         }
//     }
// }

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
