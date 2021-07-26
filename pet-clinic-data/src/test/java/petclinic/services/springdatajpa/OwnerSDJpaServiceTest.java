package petclinic.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import petclinic.model.Owner;
import petclinic.repositories.OwnerRepository;
import petclinic.repositories.PetRepository;
import petclinic.repositories.PetTypeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

//to extendwith mockito Extension & sets up the JUnit 5 environment for Mockito
@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1l).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        //add in 2 owners
        returnOwnersSet.add(Owner.builder().id(1l).build());
        returnOwnersSet.add(Owner.builder().id(2l).build());

        //when findAll is called, I want to return back the owner set
        //so the ownerRepository is a mock being managed by Mockito
        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        //this will invoke the mock and we get back a set of owners from that
        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        //anyLong(): any Long value can go in there
        //working with the ownerRepository mock
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        assertNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);

        //could be delete, just showing how to use verify and make sure that
        //a mock interaction did occur
        verify(ownerRepository).save(any());
        //verify: check that certain behavior happened/specified method are called
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        //default is 1 times
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        //check if only 1 interaction
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    //if pass this test, telling us the mocks are injected and service got created
    //Mockito is running properly
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
        Owner smith = service.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, smith.getLastName());
        verify(ownerRepository).findByLastName(any());
    }
}