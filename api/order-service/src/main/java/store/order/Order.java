package store.order;

import java.util.Date;
import java.util.List;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import store.account.AccountOut;

@Builder
@Data 
@Accessors(fluent = true)
public class Order implements Serializable {

    private String id;
    private Date date;
    private List<OrderItem> items;
    private AccountOut account;
    private Double total;
    
}
