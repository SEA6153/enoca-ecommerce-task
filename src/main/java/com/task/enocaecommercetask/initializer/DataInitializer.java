package com.task.enocaecommercetask.initializer;

import com.task.enocaecommercetask.model.Cart;
import com.task.enocaecommercetask.model.Customer;
import com.task.enocaecommercetask.model.Product;
import com.task.enocaecommercetask.repostories.CartRepository;
import com.task.enocaecommercetask.repostories.CustomerRepository;
import com.task.enocaecommercetask.repostories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public DataInitializer(CustomerRepository customerRepository,
                           ProductRepository productRepository,
                           CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        if (customerRepository.count() == 0) {
            Customer customer1 = new Customer();
            customer1.setCustomerName("Samet");
            customer1.setCustomerEmail("samet@example.com");
            customerRepository.save(customer1);

            Customer customer2 = new Customer();
            customer2.setCustomerName("Ege");
            customer2.setCustomerEmail("ege@example.com");
            customerRepository.save(customer2);
        }


        if (productRepository.count() == 0) {
            Product product1 = new Product();
            product1.setProductName("Laptop");
            product1.setProductPrice(1000.00);
            product1.setProductStock(50);
            productRepository.save(product1);

            Product product2 = new Product();
            product2.setProductName("Smartphone");
            product2.setProductPrice(500.00);
            product2.setProductStock(100);
            productRepository.save(product2);
        }


        if (cartRepository.count() == 0) {
            for (Customer customer : customerRepository.findAll()) {
                Cart cart = new Cart();
                cart.setCustomer(customer);
                cartRepository.save(cart);
            }
        }

        System.out.println("Veritabanına başlangıç verileri eklendi.");
    }
}
