package com.store.services;

import com.mysql.cj.xdevapi.Statement;
import com.store.models.Order;
import com.store.models.OrderItem;
import com.store.utils.DatabaseUtility;

import java.sql.Connection;
import java.sql.Statement;
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

    public void placeOrder(Order order) throws SQLException {
        String insertOrderQuery = "INSERT INTO orders (customer_id, order_date) VALUES (?, ?)";
        String insertOrderItemQuery = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseUtility.getConnection();
                PreparedStatement orderStmt = connection.prepareStatement(insertOrderQuery,
                        Statement.RETURN_GENERATED_KEYS)) {
            orderStmt.setInt(1, order.getCustomerId());
            orderStmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            orderStmt.executeUpdate();

            try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    for (OrderItem item : order.getOrderItems()) {
                        try (PreparedStatement orderItemStmt = connection.prepareStatement(insertOrderItemQuery)) {
                            orderItemStmt.setInt(1, orderId);
                            orderItemStmt.setInt(2, item.getProductId());
                            orderItemStmt.setInt(3, item.getQuantity());
                            orderItemStmt.executeUpdate();
                        }
                    }
                }
            }
        }
    }
}
