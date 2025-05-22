package store.order;

import store.product.ProductOut;

public class OrderParser {

    public static Order to(OrderIn in) {
        return in == null ? null
                : Order.builder()
                        .items(in.items().stream().map(OrderParser::to).toList())
                        .build();
    }

    public static OrderOut to(Order o) {
        return o == null ? null
                : OrderOut.builder()
                        .id(o.id())
                        .date(o.date())
                        .items(o.items().stream().map(OrderParser::to).toList())
                        .total(o.total())
                        .build();
    }

    public static OrderItem to(OrderItemIn in) {
        return in == null ? null
                : OrderItem.builder()
                        .product(ProductOut.builder().id(in.idProduct()).build())
                        .quantity(in.quantity())
                        .build();
    }

    public static OrderItemOut to(OrderItem o) {
        return o == null ? null
                : OrderItemOut.builder()
                        .id(o.id())
                        .product(ProductOut.builder().id(o.product().id()).build())
                        .quantity(o.quantity())
                        .total(o.total())
                        .build();
    }

}