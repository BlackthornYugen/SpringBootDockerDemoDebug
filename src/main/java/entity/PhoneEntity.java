package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "phone")
public class PhoneEntity {
    private Long id;

    @Column(unique = true, updatable = false)
    @Pattern(regexp = "^(?:\\+?\\d{1}\\s?)?\\(?(\\d{3})\\)?-?\\s?(\\d{3})-?\\s?(\\d{4})$")
    String phoneNumber;

    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
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
