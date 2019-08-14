package com.orders.services;

import com.orders.models.Customers;

import java.util.List;

public interface CustomersService {

    List<Customers> findCustomers();

    Customers findCustomerByName(String name);

    Customers save(Customers customer);

    Customers update(Customers customer, long id);

    void delete(long id);

}
