package store.order;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import store.product.ProductOut;

@Builder
@Data 
@Accessors(fluent = true)
public class OrderItem {

    private String id;
    private ProductOut product;
    private Order order;
    private int quantity;
    private Double total;  // subtotal (price × qty)
    
}
