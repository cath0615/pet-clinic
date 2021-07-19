package petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    //when there is request come into the root or root slash context or index, index.html,
    //they will match to this requestMapping
    @RequestMapping({"", "/", "index.html", "index"})
    //Thymeleaf will go back and look for a template called index
    //this wires up the controller for the above process
    public String index() {
        return"index";
    }

    @RequestMapping("/oups")
    public String oupsHandler() {
        return "notimplemented";
    }
}
