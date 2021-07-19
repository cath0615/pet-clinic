package petclinic.services.map;

import org.springframework.stereotype.Service;
import petclinic.model.Owner;
import petclinic.model.Pet;
import petclinic.services.OwnerService;
import petclinic.services.PetService;
import petclinic.services.PetTypeService;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

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
        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
                        //if Id is null, we need to save this pet type
                        if (pet.getPetType().getId() == null) {
                            //take the pet and set the petType to the save pet type which is a term from
                            //the pet service and get that pet type from the pet so we can get an ID
                            //assigned to this map object
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required");
                    }

                    if(pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }
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
