package com.orders.controllers;

import com.orders.models.Customers;
import com.orders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomersController {

    @Autowired
    private CustomersService customerService;

    //localhost:2019/customer/order
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers(){
        List<Customers> myCustomers = customerService.findCustomers();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    //localhost:2019/customer/name/{name}
    @GetMapping(value = "/name/{name}", produces = {"application/json"})
    public ResponseEntity<?> listCustomerByName(@PathVariable String name){
        Customers myCustomer = customerService.findCustomerByName(name);
        return new ResponseEntity<>(myCustomer, HttpStatus.OK);
    }



}
