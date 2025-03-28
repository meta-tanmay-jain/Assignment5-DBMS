import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EcommerceQueryHandler {
    // Assignment 1: Fetch Shipped Orders for a User
    public List<OrderDTO> getUserShippedOrders(int userId) throws SQLException {
        List<OrderDTO> orders = new ArrayList<>();
        String query = "SELECT OrderID, OrderDate, TotalAmount " +
                       "FROM `Order` " +
                       "WHERE UserID = ? AND Status = 'Shipped' " +
                       "ORDER BY OrderDate ASC";
        
        try (Connection conn = DatabaseConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrderDTO order = new OrderDTO(
                        rs.getInt("OrderID"),
                        rs.getTimestamp("OrderDate").toLocalDateTime(),
                        rs.getBigDecimal("TotalAmount")
                    );
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            // Proper exception handling
            throw new SQLException("Error fetching shipped orders: " + e.getMessage(), e);
        }
        
        return orders;
    }

    // Assignment 2: Batch Insert Product Images
    public int batchInsertProductImages(List<ProductImageDTO> images) throws SQLException {
        String query = "INSERT INTO Image (ProductID, URL, AltText, DisplayOrder) " +
                       "VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            conn.setAutoCommit(false);
            
            for (ProductImageDTO image : images) {
                pstmt.setInt(1, image.getProductId());
                pstmt.setString(2, image.getUrl());
                pstmt.setString(3, image.getAltText());
                pstmt.setInt(4, image.getDisplayOrder());
                pstmt.addBatch();
            }
            
            int[] result = pstmt.executeBatch();
            conn.commit();
            
            return result.length;
        } catch (SQLException e) {
            throw new SQLException("Error in batch image insertion: " + e.getMessage(), e);
        }
    }

    // Assignment 3: Delete Unordered Products
    public int deleteUnorderedProducts() throws SQLException {
        String query = "DELETE FROM Product WHERE ProductID NOT IN (" +
                       "    SELECT DISTINCT ProductID FROM OrderItem oi " +
                       "    JOIN `Order` o ON oi.OrderID = o.OrderID " +
                       "    WHERE o.OrderDate >= DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR)" +
                       ")";
        
        try (Connection conn = DatabaseConnectionUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new SQLException("Error deleting unordered products: " + e.getMessage(), e);
        }
    }

    // Assignment 4: Top Parent Categories with Child Count
    public List<CategoryDTO> getTopParentCategories() throws SQLException {
        List<CategoryDTO> categories = new ArrayList<>();
        String query = "SELECT c.Name AS CategoryTitle, " +
                       "       (SELECT COUNT(*) FROM Category child WHERE child.ParentCategoryID = c.CategoryID) AS ChildCount " +
                       "FROM Category c " +
                       "WHERE c.ParentCategoryID IS NULL " +
                       "ORDER BY c.Name ASC";
        
        try (Connection conn = DatabaseConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                CategoryDTO category = new CategoryDTO(
                    rs.getString("CategoryTitle"),
                    rs.getInt("ChildCount")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching top parent categories: " + e.getMessage(), e);
        }
        
        return categories;
    }
}