package rest;

import entity.CustomerEntity;
import entity.PhoneEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rep.CustomerRepository;
import rep.PhoneRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public Iterable<CustomerEntity> findAll1() {
        return customerRepository.findAll();
    }

    @RequestMapping("/post")
    public Iterable<CustomerEntity> postEntity() {
        CustomerEntity customerEntity = new CustomerEntity();
        PhoneEntity phoneEntity = new PhoneEntity();
        customerEntity.getPhones().add(phoneEntity);
        phoneRepository.save(phoneEntity);
        customerRepository.save(customerEntity);
        return customerRepository.findAll();
    }
}