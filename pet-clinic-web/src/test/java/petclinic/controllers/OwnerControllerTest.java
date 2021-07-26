package petclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import petclinic.model.Owner;
import petclinic.services.OwnerService;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//set up and initialize mockito for us
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    //@InjectMocks creates an instance of the class and injects the mocks
    //that are created with the @Mock (or @Spy ) annotations into this instance.
    OwnerController controller;

    Set<Owner> owners;

    //initialize the Spring MVC and this sets up the controller for testing
    MockMvc mockMVC;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1l).build());
        owners.add(Owner.builder().id(2l).build());
        //for each test method, it initializes a mock environment for our controller
        //so we can test many interactions with it
        mockMVC = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listOwners() throws Exception {
        //sets up mock interaction
        when(ownerService.findAll()).thenReturn(owners);
        mockMVC.perform(get("/owners/index"))
                //isOk is an HTTP status of 200
                .andExpect((status()).isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void listOwnersByIndex() throws Exception {
        //sets up mock interaction
        when(ownerService.findAll()).thenReturn(owners);
        mockMVC.perform(get("/owners/index"))
                .andExpect((status()).isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
        verifyZeroInteractions(ownerService);
    }

    @Test
    void findOwners() throws Exception {
        mockMVC.perform(get("/owners/find"))
                .andExpect((status().isOk()))
                .andExpect(view().name("notimplemented"));
        //verify with the ownerservice mock because findOwners() should not be interacting
        //with the OwnerService
        verifyZeroInteractions(ownerService);
    }
}