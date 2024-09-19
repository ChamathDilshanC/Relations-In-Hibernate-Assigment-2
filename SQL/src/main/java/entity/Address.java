package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    //owning side of the relationship
    @ManyToOne
    @JoinColumn(name = "Customer_Id")
    private Customer cid;
}
