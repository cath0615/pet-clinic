package petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//assign everything to owners first: prefixing at class level
@RequestMapping("/owner")
@Controller
public class OwnerController {
    @RequestMapping({"", "/", "/index", "/index.html"})
    public String ListOwners() {
        return "owner/index";
    }
}
