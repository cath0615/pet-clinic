package petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import petclinic.model.Speciality;

public interface SpecialtyRepository extends CrudRepository<Speciality, Long> {
}
