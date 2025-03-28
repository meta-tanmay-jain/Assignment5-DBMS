import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProductImageDTO {
    private int imageId;
    private int productId;
    private String url;
    private String altText;
    private int displayOrder;

    // Constructors, getters, setters
    public ProductImageDTO(int productId, String url, String altText, int displayOrder) {
        this.productId = productId;
        this.url = url;
        this.altText = altText;
        this.displayOrder = displayOrder;
    }

    // Getters and setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getAltText() { return altText; }
    public void setAltText(String altText) { this.altText = altText; }
    public int getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(int displayOrder) { this.displayOrder = displayOrder; }
}