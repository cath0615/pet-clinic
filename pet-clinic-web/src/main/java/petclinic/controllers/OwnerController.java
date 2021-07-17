package petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import petclinic.services.OwnerService;

//assign everything to owners first: prefixing at class level
@RequestMapping("/owner")
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    //adding constructor injection
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //by adding Model, spring MVC is automatically going to add a Model
    //when it calls this method to list
    @RequestMapping({"", "/", "/index", "/index.html"})
    public String ListOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owner/index";
    }
}
