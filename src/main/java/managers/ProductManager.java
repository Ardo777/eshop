package managers;

import db.DBConnectionProvider;
import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private static CategoryManager categoryManager = new CategoryManager();

    public void add(Product product) {
        String sql = "INSERT INTO product(id,name,description,price,count,category_id) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getCount());
            preparedStatement.setInt(6, product.getCategory().getId());
            preparedStatement.executeUpdate();
            ResultSet generateKey = preparedStatement.getGeneratedKeys();
            if (generateKey.next()) {
                product.setId(generateKey.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editProduct(Product product) {

        if (getProductById(product.getId()) == null) {
            System.out.println("this product where id=" + product.getId() + " doesn't exist!");
            return;
        }
        String sql = "UPDATE category SET name=? WHERE id=" + product.getId();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getCount());
            preparedStatement.setInt(6, product.getCategory().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Product.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .price(resultSet.getDouble("price"))
                        .count(resultSet.getInt("count"))
                        .category(categoryManager.getCategoryById(resultSet.getInt("category_id")))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        List<Product> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Product product = Product.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .price(resultSet.getDouble("price"))
                        .count(resultSet.getInt("count"))
                        .category(categoryManager.getCategoryById(resultSet.getInt("category_id")))
                        .build();
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }

    public void deleteProductById(int id) {
        if (getProductById(id) == null) {
            System.out.println("this product where id=" + id + " doesn't exist!");
            return;
        }
        String sql = "DELETE FROM product WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int sumProducts() {
        String sql = "SELECT COUNT(price) AS count FROM product";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int maxPriceOfProducts() {
        String sql = "SELECT MAX(price) AS max_price FROM product";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("max_price");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int minPriceOfProducts() {
        String sql = "SELECT MIN(price) AS min_price FROM product";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("min_price");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int avgPriceOfProducts() {
        String sql = "SELECT AVG(price) AS avg_price FROM product";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("avg_price");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}


