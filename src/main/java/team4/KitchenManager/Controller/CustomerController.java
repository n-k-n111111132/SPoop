
package team4.KitchenManager.Controller;
import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.Model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    DatabaseConnector conn;

    public CustomerController() {
        conn = new DatabaseConnector();
        }
    public CustomerController(DatabaseConnector connector){
        this.conn = connector;
        }

    public List<Customer> getAll() {
        var _query = "SELECT * FROM customers";
        List<Customer> customers = new ArrayList<>();
        try {
            var statement = conn.getConnector().prepareStatement(_query);
            var _result = statement.executeQuery();
                while (_result.next()) {
                    var _target = new Customer();
                        _target.setId(_result.getString(1));
                        _target.setFirstName(_result.getString(2));
                        _target.setLastName(_result.getString(3));
                        _target.setPhoneNumber(_result.getString(4));
                        customers.add(_target);
                        }
            _result.close();
            statement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
                }
        return customers;
        }
    public Customer getCustomer(String id){
        var _query = "SELECT * FROM customers WHERE id = ?";
        var _target = new Customer();
        try {
            var _statement = conn.getConnector().prepareStatement(_query);
                _statement.setString(1, id);
            var _result = _statement.executeQuery();
                while (_result.next()) {
                    _target.setId(_result.getString(1));
                    _target.setFirstName(_result.getString(2));
                    _target.setLastName(_result.getString(3));
                    _target.setPhoneNumber(_result.getString(4));
                    }
            _result.close();
            _statement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
                }
        return _target;
        }

    public List<String> getDishesUsingCustomer(int customerId) {
        List<String> dishes = new ArrayList<>();
        try {
            PreparedStatement statement = conn.getConnector().prepareStatement("SELECT dish_name FROM Customer_dish WHERE customer_id = ?");
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dishes.add(resultSet.getString("dish_name"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dishes;
    }

    // public void addCustomer(Customer newCustomer) {
    //     try {
    //         PreparedStatement statement = conn.getConnector().prepareStatement("INSERT INTO customer (id, name, phone) VALUES (?, ?, ?)");
    //         statement.setInt(1, newCustomer.getID());
    //         statement.setString(2, newCustomer.getName());
    //         statement.setString(3, newCustomer.getPhone());
    //         statement.executeUpdate();
    //         statement.close();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    public void editCustomerByID(int customerId, int quantity) {
        try {
            PreparedStatement statement = conn.getConnector().prepareStatement("UPDATE customer SET used_quantity = ? WHERE id = ?");
            statement.setInt(1, quantity);
            statement.setInt(2, customerId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCustomerByName(String customerName, int quantity) {
        try {
            PreparedStatement statement = conn.getConnector().prepareStatement("UPDATE customer SET used_quantity = ? WHERE name = ?");
            statement.setInt(1, quantity);
            statement.setString(2, customerName);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCustomerByPhone(String customerPhone, int quantity) {
        try {
            PreparedStatement statement = conn.getConnector().prepareStatement("UPDATE customer SET used_quantity = ? WHERE phone = ?");
            statement.setInt(1, quantity);
            statement.setString(2, customerPhone);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomerByID(int customerId) {
        try {
            PreparedStatement statement = conn.getConnector().prepareStatement("DELETE FROM customer WHERE id = ?");
            statement.setInt(1, customerId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomerByName(String customerName) {
        try {
            PreparedStatement statement = conn.getConnector().prepareStatement("DELETE FROM customer WHERE name = ?");
            statement.setString(1, customerName);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomerByPhone(String customerPhone) {
        try {
            PreparedStatement statement = conn.getConnector().prepareStatement("DELETE FROM customer WHERE phone = ?");
            statement.setString(1, customerPhone);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> searchCustomerByName(String name) {
        List<Customer> searchResults = new ArrayList<>();
        try {
            PreparedStatement statement = conn.getConnector().prepareStatement("SELECT * FROM customer WHERE name LIKE ?");
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Customer customer = new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("phone"));
                // customer.setUsedQuantity(resultSet.getInt("used_quantity"));
                // searchResults.add(customer);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResults;
    }
    }