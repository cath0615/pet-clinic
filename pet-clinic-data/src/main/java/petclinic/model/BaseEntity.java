package petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
//established this class as a base class to JPA so other classes will be inheriting this class
//we don't need this specific class mapped to the database
public class BaseEntity implements Serializable {

    @Id //tells JPA this is the id value
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
