package store.order;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
 */

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItemModel, String> {

    List<OrderItemModel> findByIdOrder(String idOrder);

}
