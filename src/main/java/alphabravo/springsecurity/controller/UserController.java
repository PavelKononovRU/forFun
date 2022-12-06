/*
package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.model.Person;
import alphabravo.springsecurity.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final PersonDetailsService personDetailsService;
    @Autowired
    public UserController(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/user/{id}")
    public String toReplace(@PathVariable long id, Model model) {
        model.addAttribute("personAdm", personDetailsService.getPersonId(id));
        return "toReplace";
    }

    @GetMapping("/access")
    public String error() {
        return "access";
    }
}
*/
