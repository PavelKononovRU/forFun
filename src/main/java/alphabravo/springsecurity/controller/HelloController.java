package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.model.Person;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/HelloPage")
    public String hello(Model model, @AuthenticationPrincipal Person person) {
        model.addAttribute("person", person);
        return "HelloPage";
    }

    @GetMapping("/access")
    public String access() {
        return "/access";
    }
}
