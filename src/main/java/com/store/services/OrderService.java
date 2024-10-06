// package com.store.services;

// import com.store.models.Order;
// import com.store.models.OrderItem;
// import com.store.utils.DatabaseUtility;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.List;

// public class OrderService {

//     public void placeOrder(Order order, List<OrderItem> orderItems) throws SQLException {
//         Connection connection = DatabaseUtility.getConnection();
//         String orderQuery = "INSERT INTO orders (customer_id, order_date) VALUES (?, ?)";
//         String orderItemQuery = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

//         try {
//             connection.setAutoCommit(false); // Start transaction

//             // Check if customer exists
//             if (!customerExists(order.getCustomerId(), connection)) {
//                 throw new SQLException("Customer ID does not exist: " + order.getCustomerId());
//             }

//             // Check if all products exist
//             for (OrderItem item : orderItems) {
//                 if (item.getProductId() <= 0) {
//                     throw new SQLException("Invalid product ID: " + item.getProductId());
//                 }

//                 if (!productExists(item.getProductId(), connection)) {
//                     throw new SQLException("Product ID does not exist: " + item.getProductId());
//                 }
//             }

//             // Insert into Orders table
//             try (PreparedStatement orderStmt = connection.prepareStatement(orderQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
//                 orderStmt.setInt(1, order.getCustomerId());
//                 orderStmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
//                 orderStmt.executeUpdate();

//                 var rs = orderStmt.getGeneratedKeys();
//                 if (rs.next()) {
//                     int orderId = rs.getInt(1);

//                     // Insert into Order_Items table
//                     try (PreparedStatement itemStmt = connection.prepareStatement(orderItemQuery)) {
//                         for (OrderItem item : orderItems) {
//                             System.out.println("Adding OrderItem - Product ID: " + item.getProductId() + ", Quantity: " + item.getQuantity());

//                             itemStmt.setInt(1, orderId);
//                             itemStmt.setInt(2, item.getProductId());
//                             itemStmt.setInt(3, item.getQuantity());
//                             itemStmt.setDouble(4, item.getPrice());
//                             itemStmt.addBatch();
//                         }
//                         itemStmt.executeBatch();
//                     }
//                 }
//             }

//             connection.commit(); // Commit transaction
//         } catch (SQLException e) {
//             System.err.println("SQL error occurred: " + e.getMessage());
//             connection.rollback(); // Rollback on error
//             throw e;
//         } finally {
//             connection.setAutoCommit(true); // Restore auto-commit
//         }
//     }

//     private boolean customerExists(int customerId, Connection connection) throws SQLException {
//         String query = "SELECT COUNT(*) FROM customers WHERE customer_id = ?";
//         try (PreparedStatement stmt = connection.prepareStatement(query)) {
//             stmt.setInt(1, customerId);
//             ResultSet rs = stmt.executeQuery();
//             if (rs.next()) {
//                 return rs.getInt(1) > 0;
//             }
//         }
//         return false;
//     }

//     private boolean productExists(int productId, Connection connection) throws SQLException {
//         String query = "SELECT COUNT(*) FROM products WHERE product_id = ?";
//         try (PreparedStatement stmt = connection.prepareStatement(query)) {
//             stmt.setInt(1, productId);
//             ResultSet rs = stmt.executeQuery();
//             if (rs.next()) {
//                 return rs.getInt(1) > 0;
//             }
//         }
//         return false;
//     }
// }

// package com.store.services;

// import com.store.models.Order;
// import com.store.utils.DatabaseUtility;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// public class OrderService {
//     // Method to place an order
//     public void placeOrder(Order order) throws SQLException {
//         // Implementation from previous response
//     }

//     // Method to fetch orders for a specific customer
//     public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
//         List<Order> orders = new ArrayList<>();
//         Connection connection = DatabaseUtility.getConnection();
//         String query = "SELECT * FROM orders WHERE customer_id = ?";

//         try (PreparedStatement stmt = connection.prepareStatement(query)) {
//             stmt.setInt(1, customerId);
//             ResultSet rs = stmt.executeQuery();

//             while (rs.next()) {
//                 Order order = new Order();
//                 order.setOrderId(rs.getInt("order_id"));
//                 order.setCustomerId(rs.getInt("customer_id"));
//                 order.setOrderDate(rs.getDate("order_date"));

//                 // Fetch order items for the order
//                 order.setOrderItems(getOrderItemsByOrderId(order.getOrderId()));
//                 orders.add(order);
//             }
//         } catch (SQLException e) {
//             throw new SQLException("Error fetching orders: " + e.getMessage(), e);
//         }
//         return orders;
//     }

//     // Method to fetch order items for a specific order
//     private List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
//         List<OrderItem> orderItems = new ArrayList<>();
//         Connection connection = DatabaseUtility.getConnection();
//         String query = "SELECT * FROM order_items WHERE order_id = ?";

//         try (PreparedStatement stmt = connection.prepareStatement(query)) {
//             stmt.setInt(1, orderId);
//             ResultSet rs = stmt.executeQuery();

//             while (rs.next()) {
//                 OrderItem orderItem = new OrderItem();
//                 orderItem.setProductId(rs.getInt("product_id"));
//                 orderItem.setQuantity(rs.getInt("quantity"));
//                 orderItems.add(orderItem);
//             }
//         } catch (SQLException e) {
//             throw new SQLException("Error fetching order items: " + e.getMessage(), e);
//         }
//         return orderItems;
//     }
// }

// package com.store.services;

// import com.store.models.Order;
// import com.store.models.OrderItem;
// import com.store.utils.DatabaseUtility;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// public class OrderService {

//     public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
//         List<Order> orders = new ArrayList<>();
//         String query = "SELECT * FROM orders WHERE customer_id = ?";

//         try (Connection connection = DatabaseUtility.getConnection();
//                 PreparedStatement stmt = connection.prepareStatement(query)) {
//             stmt.setInt(1, customerId);
//             ResultSet rs = stmt.executeQuery();

//             while (rs.next()) {
//                 Order order = new Order();
//                 order.setOrderId(rs.getInt("order_id"));
//                 order.setCustomerId(rs.getInt("customer_id"));
//                 order.setOrderDate(rs.getDate("order_date"));
//                 order.setOrderItems(getOrderItemsByOrderId(order.getOrderId())); // Fetching order items
//                 orders.add(order);
//             }
//         }

//         return orders;
//     }

//     private List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
//         List<OrderItem> orderItems = new ArrayList<>();
//         String query = "SELECT * FROM order_items WHERE order_id = ?";

//         try (Connection connection = DatabaseUtility.getConnection();
//                 PreparedStatement stmt = connection.prepareStatement(query)) {
//             stmt.setInt(1, orderId);
//             ResultSet rs = stmt.executeQuery();

//             while (rs.next()) {
//                 OrderItem item = new OrderItem();
//                 item.setProductId(rs.getInt("product_id"));
//                 item.setQuantity(rs.getInt("quantity"));
//                 orderItems.add(item);
//             }
//         }

//         return orderItems;
//     }
// }

package com.store.services;

import com.store.models.Order;
import com.store.models.OrderItem;
import com.store.utils.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE customer_id = ?";

        try (Connection connection = DatabaseUtility.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setOrderDate(rs.getDate("order_date"));

                // Fetch order items while still in the same scope
                order.setOrderItems(getOrderItemsByOrderId(order.getOrderId(), connection));

                orders.add(order);
            }
        }

        return orders;
    }

    private List<OrderItem> getOrderItemsByOrderId(int orderId, Connection connection) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        String query = "SELECT * FROM order_items WHERE order_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                orderItems.add(item);
            }
        }

        return orderItems;
    }
}
