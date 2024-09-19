package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id
    private int aid;
    private String street;
    private String city;
    private String country;

    // This is the inverse side of the relationship
    //inverse side of the relationship
    @ManyToMany(mappedBy = "addresses")
    private List<Customer> customers;
}
