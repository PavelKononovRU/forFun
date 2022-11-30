package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.model.Person;
import alphabravo.springsecurity.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDetailsService personDetailsService;
    @Autowired
    public AdminController(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }
    @GetMapping("/people")
    public String getAllPersons(Model model, @AuthenticationPrincipal Person person) {
        model.addAttribute("name", person.getUsername());
       model.addAttribute("allPersons", personDetailsService.allPeople());
       return "allPersons";
    }
}
