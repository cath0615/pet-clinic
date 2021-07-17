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

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
