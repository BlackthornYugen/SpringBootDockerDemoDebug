package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Olga Pavlova on 9/16/2016.
 */
@Entity
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneEntity)) return false;
        PhoneEntity that = (PhoneEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
