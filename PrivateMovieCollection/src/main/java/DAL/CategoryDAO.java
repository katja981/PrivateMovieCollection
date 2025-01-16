package DAL;
import BE.Category;
import BE.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private DB con = new DB();

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        try (Connection c = con.DBConnection();) {
            String sql = "SELECT [id], [categoryName] FROM Category";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String categoryName = rs.getString("categoryName");

                Category category = new Category(id, categoryName);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public void addCategory(Category category) {
        String sql = "INSERT INTO Categories (name) VALUES (?)"; // Replace 'Categories' with your actual table name
        try (Connection c = con.DBConnection();
             PreparedStatement stmt = c.prepareStatement(sql)) {

            stmt.setString(1, category.getCategoryName());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


        // assign a category to a movie
    public void assignCategoryToMovie(int movieId, List<Integer> categoryIds) {
            String sql = "INSERT INTO MovieCategories (movieId, categoryId) VALUES (?, ?)"; // Replace 'MovieCategories' with your actual table name
            try (Connection c = con.DBConnection();
                 PreparedStatement stmt = c.prepareStatement(sql)) {

                for(int categoryId : categoryIds) {
                stmt.setInt(1, movieId);
                stmt.setInt(2, categoryId);
                stmt.addBatch();
                }
                stmt.executeBatch();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public void removeCategoryFromMovie(int movieId, List<Integer> categoryIds) {
        String sql = "DELETE FROM MovieCategories WHERE movieId = ? AND categoryId = ?"; // Replace 'MovieCategories' with your actual table name
        try (Connection c = con.DBConnection();
             PreparedStatement stmt = c.prepareStatement(sql)) {

            for(int categoryId : categoryIds) {
                stmt.setInt(1, movieId);
                stmt.setInt(2, categoryId);
                stmt.addBatch();
            }
            stmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
