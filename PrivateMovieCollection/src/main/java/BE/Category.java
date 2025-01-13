package BE;

public class Category {
    private int id;
    private String categoryName;

    public Category(int id, String name) {
        this.id = id;
        this.categoryName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return (categoryName != null ? categoryName : "Unknown");
    }
}