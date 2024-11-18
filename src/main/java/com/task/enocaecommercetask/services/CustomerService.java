package com.task.enocaecommercetask.services;

import com.task.enocaecommercetask.dto.CustomerDTO;
import com.task.enocaecommercetask.model.Customer;
import com.task.enocaecommercetask.repostories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void addCustomer(CustomerDTO customerDTO){

        Customer customer = new Customer();
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerEmail(customerDTO.getCustomerEmail());
        System.out.println("Saving customer: " + customerDTO.getCustomerName());
        customerRepository.save(customer);
        System.out.println("Customer saved: " + customer.getId());

    }

}
