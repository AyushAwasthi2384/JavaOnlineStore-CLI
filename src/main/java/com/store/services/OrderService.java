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
