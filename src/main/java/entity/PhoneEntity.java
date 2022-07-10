package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode
public class PhoneEntity {
    private Long id;

    @JsonBackReference
    private Set<CustomerEntity> customers = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    @Column(name = "PID", nullable = false)
    public Long getId() {
        return id;
    }

    @ManyToMany(mappedBy="phones")
    public Set<CustomerEntity> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<CustomerEntity> customers) {
        this.customers = customers;
    }
}
