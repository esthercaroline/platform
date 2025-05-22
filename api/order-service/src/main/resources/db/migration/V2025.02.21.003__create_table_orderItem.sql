CREATE TABLE order_item (
    id_order_item VARCHAR(36) NOT NULL,
    id_order VARCHAR(36) NOT NULL,
    id_product VARCHAR(36) NOT NULL,
    int_quantity INT NOT NULL,
    nr_price_product DECIMAL(10, 2) NOT NULL,
    nr_total DECIMAL(10, 2) NOT NULL,
    CONSTRAINT pk_order_item PRIMARY KEY (id_order_item)
);