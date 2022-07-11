package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
public class PhoneEntity {
    private Long id;

    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<CustomerEntity> customers = new HashSet<>();

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
}
