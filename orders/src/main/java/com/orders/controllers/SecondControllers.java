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
@RequestMapping("/data")
public class SecondControllers {
    @Autowired
    private CustomersService customerService;

    //localhost:2019/data/customer/new
    @PostMapping(value = "/customer/new")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customers customer){
        Customers newCustomer = customerService.save(customer);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{custcode}").buildAndExpand(newCustomer.getCustcode()).toUri();
        responseHeaders.setLocation(newCustomerURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //localhost:2019/data/customer/update/{custcode}
    @PutMapping(value = "/customer/update/{custcode}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customers updateCustomer, @PathVariable long custcode){
        customerService.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //localhost:2019/data/customer/delete/{custcode}
    @DeleteMapping(value = "/customer/delete/{custcode}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long custcode){
        customerService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
