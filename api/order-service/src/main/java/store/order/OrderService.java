package store.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import store.product.ProductController;
import store.product.ProductOut;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductController productController;

    public Order create(Order order) {
        order.date(new Date());
        double totalOrder = 0.0;
        List<OrderItem> newOrderItems = new ArrayList<>();

        for (OrderItem oi : order.items()) {
            ResponseEntity<ProductOut> response = productController.findById(oi.product().id());
            ProductOut product = response.getBody();

            if (product == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "O produto com id=" + oi.product().id() + " não existe.");
            }

            double itemTotal = product.price() * oi.quantity();
            totalOrder += itemTotal;

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(oi.quantity())
                    .total(itemTotal)
                    .build();

            newOrderItems.add(orderItem);
        }

        order.total(totalOrder);
        final Order savedOrder = orderRepository.save(new OrderModel(order)).to();

        newOrderItems.forEach(oi -> {
            oi.order(savedOrder);
            savedOrder.items().add(orderItemRepository.save(new OrderItemModel(oi)).to());
        });

        return savedOrder;
    }
    @Cacheable("orders")
    public Order findById(String id, String idAccount) {
        Order found = orderRepository.findByIdAndIdAccount(id, idAccount)
                .map(OrderModel::to)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        found.items(orderItemRepository.findByIdOrder(id)
                .stream()
                .map(OrderItemModel::to)
                .toList());
        return found;
    }

    public List<Order> findAll() {
        return StreamSupport
                .stream(orderRepository.findAll().spliterator(), false)
                .map(OrderModel::to)
                .toList();
    }

}