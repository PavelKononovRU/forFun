package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.model.Person;
import alphabravo.springsecurity.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class PersonController {
    private final PersonRepo personRepo;

    @Autowired
    public PersonController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @GetMapping
    public String userinfo(@AuthenticationPrincipal Person person, Model model) {
        model.addAttribute("person", personRepo.findPersonByUsername(person.getUsername()));
        return "userinfo";
    }
}
