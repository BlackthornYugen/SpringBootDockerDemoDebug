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
@EqualsAndHashCode
public class CustomerEntity {
    private Long id;

    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PhoneEntity> phones = new HashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "CID", nullable = false)
    public Long getId() {
        return id;
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable
    public Set<PhoneEntity> getPhones() {
        return phones;
    }
}
