package petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import petclinic.services.OwnerService;

//assign everything to owners first: prefixing at class level
@RequestMapping("/owners")
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
        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners() {
        return "notimplemented";
    }

    @GetMapping("/{ownerId}")
    //ModelAndView is a holder for both Model and View in the web MVC framework
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

}
