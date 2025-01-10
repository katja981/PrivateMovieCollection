package DAL;
import BE.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private final String dbFileLink = "path/to/your/database/file.db"; // Replace with your actual database path
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM Categories"; // Replace 'Categories' with your actual table name

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFileLink);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
    public void addCategory(Category category) {
        String sql = "INSERT INTO Categories (name) VALUES (?)"; // Replace 'Categories' with your actual table name
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFileLink);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category.getName());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        }
        // assign a category to a movie
        public void assignCategoryToMovie(int movieId, int categoryId) {
            String sql = "INSERT INTO MovieCategories (movieId, categoryId) VALUES (?, ?)"; // Replace 'MovieCategories' with your actual table name
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFileLink);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, movieId);
                stmt.setInt(2, categoryId);
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
