public class CategoryDTO {
    private String categoryTitle;
    private int childCategoryCount;

    // Constructors, getters, setters
    public CategoryDTO(String categoryTitle, int childCategoryCount) {
        this.categoryTitle = categoryTitle;
        this.childCategoryCount = childCategoryCount;
    }

    // Getters and setters
    public String getCategoryTitle() { return categoryTitle; }
    public void setCategoryTitle(String categoryTitle) { this.categoryTitle = categoryTitle; }
    public int getChildCategoryCount() { return childCategoryCount; }
    public void setChildCategoryCount(int childCategoryCount) { this.childCategoryCount = childCategoryCount; }
}