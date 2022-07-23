package entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "customer")
@EqualsAndHashCode
public class CustomerEntity {
    private Long id;

    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PhoneEntity> phones = new HashSet<>();

    @Id
    @SequenceGenerator(name = "CUSTOMER_SEQ_GEN", sequenceName = "CUSTOMER_SEQ", initialValue = 100_000_000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ_GEN")
    @Column(name = "CID", nullable = false)
    public Long getId() {
        return id;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_phone",
            foreignKey = @ForeignKey(name = "FK_phone_customer_customer"),
            inverseForeignKey = @ForeignKey(name = "FK_phone_customer_phone"),
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "cid")},
            inverseJoinColumns = {@JoinColumn(name = "phones_id", referencedColumnName = "pid")}
    )
    public Set<PhoneEntity> getPhones() {
        return phones;
    }
}
