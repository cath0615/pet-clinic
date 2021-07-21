package petclinic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
//established this class as a base class to JPA so other classes will be inheriting this class
//we don't need this specific class mapped to the database
public class BaseEntity implements Serializable {

    @Id //tells JPA this is the id value
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
