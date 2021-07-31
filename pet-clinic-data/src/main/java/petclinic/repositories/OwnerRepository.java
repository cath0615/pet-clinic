package petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import petclinic.model.Owner;

import java.util.List;

//because these types are JPA entities, Spring data JPA is going to provide us instances of these
//at runtime automatically, so we can use them in spring context
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);
}
