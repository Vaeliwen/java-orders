package com.orders.repos;

import com.orders.models.Orders;
import org.springframework.data.repository.CrudRepository;


public interface OrdersRepository extends CrudRepository<Orders, Long> {
}
