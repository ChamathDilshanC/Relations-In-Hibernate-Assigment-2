package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class  Customer {
    @Id
    private int cid;
    private String name;

    //inverse side of the relationship
    @OneToMany (mappedBy = "cid")
    private List<Address> addresses;
}
