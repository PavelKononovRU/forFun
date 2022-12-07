package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.model.Person;
import alphabravo.springsecurity.service.PersonDetailsService;
import alphabravo.springsecurity.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@Controller
public class MainAdminController {
    private final RoleServiceImpl roleService;

    private final PersonDetailsService personDetailsService;

    @Autowired
    public MainAdminController(RoleServiceImpl roleService, PersonDetailsService personDetailsService) {
        this.roleService = roleService;
        this.personDetailsService = personDetailsService;
    }

    @GetMapping
    public String peopleInfo(@AuthenticationPrincipal Person person, Model model) {
        model.addAttribute("currentPerson", personDetailsService.loadUserByUsername(person.getUsername()));
        model.addAttribute("people", personDetailsService.allPersons());
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("personForCreate", new Person());
        return "admin";
    }

    /*Удаление*/
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        personDetailsService.remove(id);
        return "redirect:/admin";
    }

    /*Обновление*/
    @PatchMapping("/update/{id}")
    public String update(@PathVariable long id, Person person) {
        personDetailsService.toUpdatePerson(id, person);
        return "redirect:/admin";
    }

    /*Создание*/
    @PostMapping("/create/new")
    public String create(@ModelAttribute("personForCreate") Person person) {
        personDetailsService.savePerson(person);
        return "redirect:/admin";
    }
}
