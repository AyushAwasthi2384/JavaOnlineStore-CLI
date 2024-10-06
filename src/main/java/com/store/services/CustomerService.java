    package com.store.services;

    import com.store.models.Customer;
    import com.store.utils.DatabaseUtility;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;

    public class CustomerService {
        // Method to add a new customer to the database
        public void addCustomer(Customer customer) throws SQLException {
            Connection connection = DatabaseUtility.getConnection();
            String query = "INSERT INTO customers (name, email, phone) VALUES (?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, customer.getName());
                stmt.setString(2, customer.getEmail());
                stmt.setString(3, customer.getPhone());
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException("Error adding customer: " + e.getMessage(), e);
            }
        }

        // Method to get a customer by ID
        public Customer getCustomerById(int customerId) throws SQLException {
            Connection connection = DatabaseUtility.getConnection();
            String query = "SELECT * FROM customers WHERE customer_id = ?";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, customerId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(rs.getInt("customer_id"));
                    customer.setName(rs.getString("name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhone(rs.getString("phone"));
                    return customer;
                } else {
                    throw new SQLException("Customer not found with ID: " + customerId);
                }
            }
        }

        // Method to update customer details
        public void updateCustomer(Customer customer) throws SQLException {
            Connection connection = DatabaseUtility.getConnection();
            String query = "UPDATE customers SET name = ?, email = ?, phone = ? WHERE customer_id = ?";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, customer.getName());
                stmt.setString(2, customer.getEmail());
                stmt.setString(3, customer.getPhone());
                stmt.setInt(4, customer.getCustomerId());
                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated == 0) {
                    throw new SQLException("Customer not found with ID: " + customer.getCustomerId());
                }
            } catch (SQLException e) {
                throw new SQLException("Error updating customer: " + e.getMessage(), e);
            }
        }

        // Method to delete a customer by ID
        public void deleteCustomer(int customerId) throws SQLException {
            Connection connection = DatabaseUtility.getConnection();
            String query = "DELETE FROM customers WHERE customer_id = ?";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, customerId);
                int rowsDeleted = stmt.executeUpdate();

                if (rowsDeleted == 0) {
                    throw new SQLException("Customer not found with ID: " + customerId);
                }
            } catch (SQLException e) {
                throw new SQLException("Error deleting customer: " + e.getMessage(), e);
            }
        }
    }
