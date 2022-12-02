package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.model.Person;
import alphabravo.springsecurity.service.PersonDetails;
import alphabravo.springsecurity.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDetailsService personDetailsService;

    private final PersonDetails personDetails;

    @Autowired
    public AdminController(PersonDetailsService personDetailsService, PersonDetails personDetails) {
        this.personDetailsService = personDetailsService;
        this.personDetails = personDetails;

    }

    @GetMapping("/people")
    public String getAllPersons(Model model) {
        model.addAttribute("allPersons", personDetailsService.allPersons());
        return "allPersons";
    }

    @GetMapping("/people/{id}")
    public String getUser(@PathVariable long id, Model model) {
        model.addAttribute("person", personDetailsService.getPersonId(id));
        return "user";
    }

    /*Удаление*/
    @DeleteMapping("/people/{id}")
    public String delete(@PathVariable long id) {
        personDetailsService.remove(id);
        return "redirect:/admin/people/";
    }

    /*Создание*/
    @GetMapping("/people/newPerson")
    public String addNewUser(@ModelAttribute("newPerson") Person person) {
        return "create";
    }

    @PostMapping("people/newPerson/")
    public String saveUser(@ModelAttribute("newPerson") Person person) {
        personDetails.savePerson(person);
        return "redirect:/admin/people/";
    }

    /*Обновление*/

    @GetMapping("/people/{id}/edit")
    public String edit(Model model, @PathVariable long id) {
        model.addAttribute("editPerson", personDetailsService.getPersonId(id));
        return "edit";
    }

    @PostMapping("/people/{id}")
    public String updateUser(@ModelAttribute("editPerson") Person toUpdatePerson,
                             @PathVariable("id") long id) {
        personDetailsService.toUpdatePerson(id, toUpdatePerson);
        return "redirect:/admin/people/";
    }
}
