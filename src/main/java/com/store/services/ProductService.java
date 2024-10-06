// package com.store.services;

// import com.store.models.Product;
// import com.store.utils.DatabaseUtility;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class ProductService {
//     public void addProduct(Product product) throws SQLException {
//         String query = "INSERT INTO products (name, category, price, stock_quantity) VALUES (?, ?, ?, ?)";

//         try (Connection connection = DatabaseUtility.getConnection();
//                 PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

//             // Set parameters
//             stmt.setString(1, product.getName());
//             stmt.setString(2, product.getCategory());
//             stmt.setDouble(3, product.getPrice());
//             stmt.setInt(4, product.getStockQuantity());

//             // Execute update
//             stmt.executeUpdate();

//             // Retrieve generated keys
//             try (ResultSet rs = stmt.getGeneratedKeys()) {
//                 if (rs.next()) {
//                     product.setProductId(rs.getInt(1)); // Set the product ID to the newly created product
//                 }
//             }
//         }
//     }

//     public void updateProductStock(int productId, int newQuantity) throws SQLException {
//         String query = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";

//         try (Connection connection = DatabaseUtility.getConnection();
//                 PreparedStatement stmt = connection.prepareStatement(query)) {
//             stmt.setInt(1, newQuantity);
//             stmt.setInt(2, productId);
//             stmt.executeUpdate();
//         }
//     }
// }

package com.store.services;

import com.store.models.Product;
import com.store.utils.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO products (name, category, price, stock_quantity) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtility.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Set parameters
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStockQuantity());

            // Execute update
            stmt.executeUpdate();

            // Retrieve generated keys
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    product.setProductId(rs.getInt(1)); // Set the product ID to the newly created product
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error adding product: " + e.getMessage(), e);
        }
    }

    public void updateProductStock(int productId, int newQuantity) throws SQLException {
        String query = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";

        try (Connection connection = DatabaseUtility.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, productId);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Product not found with ID: " + productId);
            }
        } catch (SQLException e) {
            throw new SQLException("Error updating product stock: " + e.getMessage(), e);
        }
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection connection = DatabaseUtility.getConnection();
                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                productList.add(product);
            }
        }
        return productList;
    }
}
