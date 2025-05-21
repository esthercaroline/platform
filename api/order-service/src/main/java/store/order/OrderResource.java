package store.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import store.account.AccountOut;

@RestController
public class OrderResource implements OrderController {

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseEntity<OrderOut> create(OrderIn orderin, String idAccount) {
        Order o = OrderParser.to(orderin);
        o.account(AccountOut.builder().id(idAccount).build());
        Order created = orderService.create(o);
        return ResponseEntity.ok().body(OrderParser.to(created));
    }

    @Override
    public ResponseEntity<List<OrderOut>> findAll() {
        return ResponseEntity
                .ok()
                .body(orderService.findAll().stream().map(OrderParser::to).toList());
    }

    @Override
    public ResponseEntity<OrderOut> findById(String idOrder, String idAccount) {
        Order order = orderService.findById(idOrder, idAccount);
        return ResponseEntity.ok().body(OrderParser.to(order));
    }

}
