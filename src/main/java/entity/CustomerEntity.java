package entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode
public class CustomerEntity {
    private Long id;

    @JsonManagedReference
    private Set<PhoneEntity> phones = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setPhones(Set<PhoneEntity> phones) {
        this.phones = phones;
    }
}
