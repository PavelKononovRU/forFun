package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.ExceptionHandler.NoSuchPersonException;
import alphabravo.springsecurity.ExceptionHandling.ExceptionInformation;
import alphabravo.springsecurity.model.Person;
import alphabravo.springsecurity.model.Role;
import alphabravo.springsecurity.service.PersonDetailsService;
import alphabravo.springsecurity.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class MainAdminController {
    private final RoleServiceImpl roleService;

    private final PersonDetailsService personDetailsService;

    @Autowired
    public MainAdminController(RoleServiceImpl roleService, PersonDetailsService personDetailsService) {
        this.roleService = roleService;
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/people")
    public ResponseEntity<List<Person>> peopleInfo() {
        List<Person> people = personDetailsService.allPersons();
        return ((people != null) && (!people.isEmpty()))
                ? new ResponseEntity<>(people, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Person person = personDetailsService.getPersonId(id);
        return (person != null)
                ? new ResponseEntity<>(person, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable Long id) {
        if (personDetailsService.getPersonId(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        personDetailsService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/people")
    public ResponseEntity<Person> addNewPerson(@RequestBody Person person) {
        personDetailsService.savePerson(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    /*Обновление пользователя*/
    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable long id) {
        if (personDetailsService.getPersonId(id) == null) {
            return new ResponseEntity<>(person, HttpStatus.NOT_FOUND);
        }
        personDetailsService.toUpdatePerson(id, person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionInformation> handler(NoSuchPersonException exception) {
        ExceptionInformation data = new ExceptionInformation();
        data.setInform(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    //
    @GetMapping("/authentic")
    public ResponseEntity<Person> getAuthentic(@AuthenticationPrincipal Person person) {
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> allRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ((roles != null) && (!roles.isEmpty()))
                ? new ResponseEntity<>(roles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

