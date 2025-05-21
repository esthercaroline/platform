package store.order;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/*
 * https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
 */

@Repository
public interface OrderRepository extends CrudRepository<OrderModel, String> {
    Optional<OrderModel> findByIdAndIdAccount(String id, String accountId);
}
