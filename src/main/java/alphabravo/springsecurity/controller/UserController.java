package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.model.Person;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    @PostMapping("/userinfo")
    public String userInfo(Model model, @AuthenticationPrincipal Person person) {
        model.addAttribute("person", person);
        return "user";
    }

    @GetMapping("/error")
    public String error() {
        return "redirect:/HelloPage";
    }
}
