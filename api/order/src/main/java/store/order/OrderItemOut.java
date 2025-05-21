package store.order;

import lombok.Builder;
import lombok.experimental.Accessors;
import store.product.ProductOut;

@Builder
@Accessors(fluent = true)
public record OrderItemOut(
                ProductOut product,
                String id,
                int quantity,
                Double total) {
}
