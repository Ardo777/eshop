package managers;

import db.DBConnectionProvider;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Category category) {
        String sql = "INSERT INTO category(id,name) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getName());
            preparedStatement.executeUpdate();
            ResultSet generateKey = preparedStatement.getGeneratedKeys();
            if (generateKey.next()) {
                category.setId(generateKey.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editCategoryById(Category category) {
        if (getCategoryById(category.getId()) == null) {
            System.out.println("this category where id=" + category.getId() + " doesn't exist!");
            return;
        }
        String sql = "UPDATE category SET name=? WHERE id=" + category.getId();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM category";
        List<Category> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Category category = Category.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                list.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }

    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM category WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Category.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteCategoryById(int id) {
        if (getCategoryById(id) == null) {
            System.out.println("this category where id=" + id + " doesn't exist!");
            return;
        }
        String sql = "DELETE FROM category WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
