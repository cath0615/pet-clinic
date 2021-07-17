package petclinic.services.map;

import org.springframework.stereotype.Service;
import petclinic.model.Owner;
import petclinic.services.OwnerService;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    @Override
    public Set<Owner> findAll() {
        //because we are extending another class, we need to use super
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    //this is a map method where need to implement
    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
