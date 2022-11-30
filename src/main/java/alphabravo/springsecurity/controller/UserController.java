package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.model.Person;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping
    public String userInfo(Model model, @AuthenticationPrincipal Person person) {
        model.addAttribute("person", person);
        model.addAttribute("roles", person.getRoles());
        return "InformAboutUser";
    }
}
