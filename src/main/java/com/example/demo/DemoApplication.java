package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.CustomerEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import rep.CustomerRepository;

import javax.validation.constraints.NotNull;

@SpringBootApplication(scanBasePackages =  { "entity", "rest" })
@EnableJpaRepositories("rep")
@EntityScan("entity")
public class DemoApplication implements CommandLineRunner
{
    private final CustomerRepository customerRepository;

    private final ObjectMapper objectMapper;

    public DemoApplication(@NotNull CustomerRepository customerRepository,
                           @NotNull ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        // Getting all
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** All *******");
        stringBuilder.append(System.lineSeparator());
        for(CustomerEntity customerEntity : customerRepository.findAll())
        {
            stringBuilder.append(objectMapper.writeValueAsString(customerEntity));
            stringBuilder.append(System.lineSeparator());
        }

        System.out.println(stringBuilder);

    }
}