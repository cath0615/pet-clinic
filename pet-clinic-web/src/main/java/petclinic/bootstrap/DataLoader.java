package petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import petclinic.model.Owner;
import petclinic.services.OwnerService;
import petclinic.services.VetService;
import petclinic.services.map.OwnerServiceMap;
import petclinic.services.map.VetServiceMap;

//this becomes a spring bean and gets registered into the spring context
@Component
//when my spring context is completely up and ready, it will
//call this run method and run everything inside of it
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    //We are specifying the interfaces(owner and vetService) and any implementation
    //of the interface that's in the spring context is going to get autowired
    //so we injected these services into the constructor in spring context instead of
    //component scan(where the whole package got scanned and we need to initialize
    //services in the constructor

    //this one constructor requires spring and the spring IoC container to wire up the
    //components for us because we have added annotations to them
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
