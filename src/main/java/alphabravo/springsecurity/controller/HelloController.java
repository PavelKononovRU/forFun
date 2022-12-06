/*
package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.model.Person;
import alphabravo.springsecurity.service.PersonDetailsService;
import alphabravo.springsecurity.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {
    private final PersonDetailsService personDetailsService;
    private final RoleServiceImpl roleService;

    @Autowired
    public HelloController(PersonDetailsService personDetailsService, RoleServiceImpl roleService) {
        this.personDetailsService = personDetailsService;
        this.roleService = roleService;
    }

    @GetMapping("/HelloPage")
    public String hello(Model model, @AuthenticationPrincipal Person person) {
        model.addAttribute("person", person);
        return "HelloPage";
    }

    @GetMapping("/admin/{id}")
    public String ToReplaceForAdmin(@PathVariable long id, Model model,
                                    @AuthenticationPrincipal ) {
        model.addAttribute("person", personDetailsService.getPersonId(id));
        model.addAttribute("people", personDetailsService.allPersons());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "ToReplaceForAdmin";
    }
}
*/
