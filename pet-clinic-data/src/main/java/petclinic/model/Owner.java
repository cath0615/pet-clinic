package petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "owners")
//sets up in the owner table as owners inside the database
public class Owner extends Person {

    @Builder
    public Owner(Long id, String firstName, String lastName, String address,
                 String city, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
    }

    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;

    //we initialize this with a default value and set it to a new Hashset
    //so when we go in and add in a new pet, it will automatically add it for us
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    //set up the mapping for the Owner entity
    private Set<Pet> pets = new HashSet<>();
}
