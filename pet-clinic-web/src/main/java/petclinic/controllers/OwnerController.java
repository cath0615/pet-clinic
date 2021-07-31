package petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import petclinic.model.Owner;
import petclinic.services.OwnerService;

import java.util.List;

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

    @InitBinder
    //an annotation that is used to customize the request being sent to the controller.
    //used to initialize some binding rules
    //allow us to have the webDataBinder injected into our controller
    public void setAllowedFields(WebDataBinder dataBinder) {
        //webDataBinder: a DataBinder that binds request parameter to JavaBean objects, help us to
        //control a from post with more details
        dataBinder.setDisallowedFields("id");
        //Register fields that should not be allowed for binding
        //we don't want allow the web forms to address and manipulate the ID property. without it,
        //sb may hack it and manipulate the id property
    }

    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build()); ////The builder pattern simplifies the creation of objects
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if(owner.getLastName() == null) {
            owner.setLastName("");
        }

        //by appending the %(wild card character in SQL), it's going to do a SQL search and a like clause
        //e.g. find everything ending with ""
        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if (results.isEmpty()) {
            //(null string field, error code...)
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    //ModelAndView is a holder for both Model and View in the web MVC framework
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

}
