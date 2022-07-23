package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "phone")
public class PhoneEntity {
    private Long id;

    @Column(unique = true, updatable = false)
    @Pattern(regexp = "^(?:\\+?\\d{1}\\s?)?\\(?(\\d{3})\\)?-?\\s?(\\d{3})-?\\s?(\\d{4})$")
    String phoneNumber;

    @Id
    @GeneratedValue
    @Column(name = "PID", nullable = false)
    public Long getId() {
        return id;
    }
}
