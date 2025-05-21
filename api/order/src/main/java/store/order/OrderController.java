package store.order;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "order", url = "http://order:8080")
public interface OrderController {

    @PostMapping("/order")
    public ResponseEntity<OrderOut> create(
        @RequestBody OrderIn orderIn,
        @RequestHeader("id-account") String idAccount
    );

    @GetMapping("/order")
    public ResponseEntity<List<OrderOut>> findAll();

    @GetMapping("/order/{idOrder}")
    public ResponseEntity<OrderOut> findById(
        @PathVariable String idOrder,
        @RequestHeader("id-account") String idAccount
    );

}
