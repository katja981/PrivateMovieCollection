package BLL;

import BE.Category;
import DAL.CategoryDAO;

import java.util.List;

public class CategoryManager {

    private CategoryDAO categoryDAO;

    public CategoryManager() {
        categoryDAO = new CategoryDAO();
    }

    public List<Category> getAll() {
        try {
            return categoryDAO.getAllCategories();
        } catch (Exception e) {
            System.err.println("Error fetching categories: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
