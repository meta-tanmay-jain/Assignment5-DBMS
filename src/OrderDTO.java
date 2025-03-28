import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// Order POJO
public class OrderDTO {
    private int orderId;
    private LocalDateTime orderDate;
    private BigDecimal orderTotal;

    // Constructors, getters, setters
    public OrderDTO(int orderId, LocalDateTime orderDate, BigDecimal orderTotal) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
    }

    // Getters and setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public BigDecimal getOrderTotal() { return orderTotal; }
    public void setOrderTotal(BigDecimal orderTotal) { this.orderTotal = orderTotal; }
}
