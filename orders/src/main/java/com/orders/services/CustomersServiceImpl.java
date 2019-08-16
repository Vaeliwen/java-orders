package com.orders.services;


import com.orders.models.*;
import com.orders.repos.CustomersRepository;
import com.orders.repos.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customersService")
@Transactional
public class CustomersServiceImpl implements CustomersService {

    @Autowired
    private CustomersRepository custrepos;


    @Override
    public List<Customers> findCustomers(){
        List<Customers> list = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customers findCustomerByName(String name){
        List<Customers> list = new ArrayList<>();
        Customers foundCustomer = new Customers();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        for (Customers c : list) {
            if (c.getCustname().equals(name)) {
                foundCustomer =  c;
            }
        }
        return foundCustomer;
    }

    @Override
    public Customers save(Customers customer){
        Customers newCustomer = new Customers();

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setAgent(customer.getAgent());

        for (Orders o : customer.getOrders()){
            newCustomer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrddescription()));
        }

        return custrepos.save(newCustomer);
    }

    @Override
    public void delete(long id){
        if (custrepos.findById(id).isPresent())
        {
            custrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Customers update(Customers customer, long id) {

        Customers currentCustomer = custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (customer.getCustname() != null) {
            currentCustomer.setCustname(customer.getCustname());
        }

        if (customer.getCustcity() != null) {
            currentCustomer.setCustcity(customer.getCustcity());
        }

        if (customer.getWorkingarea() != null) {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if (customer.getCustcountry() != null) {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }

        if (customer.getGrade() != null) {
            currentCustomer.setGrade(customer.getGrade());
        }

        if (customer.getPhone() != null) {
            currentCustomer.setPhone(customer.getPhone());
        }

        if (customer.getAgent() != null) {
            currentCustomer.setAgent(customer.getAgent());
        }


        return custrepos.save(currentCustomer);
    }
}
