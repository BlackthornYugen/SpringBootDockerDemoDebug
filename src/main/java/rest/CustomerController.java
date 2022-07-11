package rest;

import entity.CustomerEntity;
import entity.PhoneEntity;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Iterable<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @RequestMapping("/post")
    public Iterable<CustomerEntity> postEntity() {
        val customerEntity = new CustomerEntity();
        customerRepository.save(customerEntity);
        return customerRepository.findAll();
    }

    @RequestMapping("/{user}/post/{phone}")
    public CustomerEntity postEntity (
            @PathVariable(value="user") long user,
            @PathVariable(value="phone") String phone
    ) throws Exception {
        val customerMaybe = customerRepository.findById(user);
        final CustomerEntity customerEntity;
        if (customerMaybe.isPresent()) {
            customerEntity = customerMaybe.get();
        } else {
            throw new Exception("Customer wasn't found.");
        }

        val phoneMaybe = phoneRepository.findPhoneEntityByPhoneNumberEquals(phone);
        final PhoneEntity phoneEntity;
        if (phoneMaybe == null) {
            phoneEntity = new PhoneEntity();
            phoneEntity.setPhoneNumber(phone);
            phoneRepository.save(phoneEntity);
        } else {
            phoneEntity = phoneMaybe;
        }

        customerEntity.getPhones().add(phoneEntity);
        customerRepository.save(customerEntity);
        return customerEntity;
    }
}