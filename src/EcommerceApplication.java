import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class EcommerceApplication {
    public static void main(String[] args) {
        EcommerceQueryHandler queryHandler = new EcommerceQueryHandler();

        try {
            // Example usage of each method

            // Assignment 1: Get Shipped Orders
            System.out.println("Assignment 1:");
            List<OrderDTO> shippedOrders = queryHandler.getUserShippedOrders(2); // Assuming user ID 1
            System.out.println("Shipped Orders:");
            shippedOrders.forEach(order -> 
                System.out.printf("Order ID: %d, Date: %s, Total: $%.2f%n", 
                    order.getOrderId(), order.getOrderDate(), order.getOrderTotal())
            );

            // Assignment 2: Batch Insert Product Images
            System.out.println("Assignment 2:");
            List<ProductImageDTO> images = Arrays.asList(
                new ProductImageDTO(1, "/images/product1_1.jpg", "Product 1 - Main Image", 1),
                new ProductImageDTO(1, "/images/product1_2.jpg", "Product 1 - Side View", 2),
                new ProductImageDTO(1, "/images/product1_3.jpg", "Product 1 - Detail", 3)
            );
            int insertedImages = queryHandler.batchInsertProductImages(images);
            System.out.println("Inserted Images: " + insertedImages);

            // Assignment 3: Delete Unordered Products
            System.out.println("Assignment 3:");
            int deletedProducts = queryHandler.deleteUnorderedProducts();
            System.out.println("Deleted Unordered Products: " + deletedProducts);

            // Assignment 4: Get Top Parent Categories
            System.out.println("Assignment 4:");
            List<CategoryDTO> topCategories = queryHandler.getTopParentCategories();
            System.out.println("Top Parent Categories:");
            topCategories.forEach(category -> 
                System.out.printf("Category: %s, Child Count: %d%n", 
                    category.getCategoryTitle(), category.getChildCategoryCount())
            );

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}