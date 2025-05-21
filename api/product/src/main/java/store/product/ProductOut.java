package store.product;

import lombok.Builder;
import lombok.experimental.Accessors;

@Builder
@Accessors(fluent = true)
public record ProductOut(
        String id,
        String name,
        Double price,
        String unit) {
    public ProductOut() {
        this(null, null, 0.0, null);
    }
}
