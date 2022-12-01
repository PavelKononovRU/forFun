package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.model.Person;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController {
    @GetMapping("info")
    public String userInfo(@AuthenticationPrincipal Person person) {
        return "user";
    }
}
