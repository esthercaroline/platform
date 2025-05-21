package store.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import store.product.ProductOut;

@Entity
@Table(name = "order_item")
@Setter
@Accessors(fluent = true)
@NoArgsConstructor
public class OrderItemModel {

    @Id
    @Column(name = "id_order_item")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "id_order")
    private String idOrder;

    @Column(name = "id_product")
    private String idProduct;

    @Column(name = "int_quantity")
    private Integer quantity;

    @Column(name = "nr_price_product")
    private Double priceProduct;

    @Column(name = "nr_total")
    private Double total;

    public OrderItemModel(OrderItem oi) {
        this.id = oi.id();
        this.idOrder = oi.order().id();
        this.idProduct = oi.product().id();
        this.quantity = oi.quantity();
        this.priceProduct = oi.product().price();
        this.total = oi.total();
    }

    public OrderItem to() {
        return OrderItem.builder()
                .id(id)
                .order(Order.builder().id(idOrder).build())
                .product(ProductOut.builder().id(idProduct).build())
                .quantity(quantity)
                .total(total)
                .build();
    }

}
