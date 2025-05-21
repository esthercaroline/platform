package store.order;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.experimental.Accessors;

@Builder
@Accessors(fluent = true)
public record OrderOut(
                String id, // id do order
                Date date,
                List<OrderItemOut> items,
                Double total) {
}
