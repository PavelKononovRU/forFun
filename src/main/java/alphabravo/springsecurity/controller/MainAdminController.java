package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.ExceptionHandler.NoSuchPersonException;
import alphabravo.springsecurity.ExceptionHandling.ExceptionInformation;
import alphabravo.springsecurity.model.Person;
import alphabravo.springsecurity.model.Role;
import alphabravo.springsecurity.repositories.RoleRepo;
import alphabravo.springsecurity.service.PersonDetailsService;
import alphabravo.springsecurity.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@CrossOrigin*/
@RequestMapping("/admin")
@RestController
public class MainAdminController {
    private final RoleServiceImpl roleService;

    private final PersonDetailsService personDetailsService;
    private final RoleRepo roleRepo;

    @Autowired
    public MainAdminController(RoleServiceImpl roleService, PersonDetailsService personDetailsService,
                               RoleRepo roleRepo) {
        this.roleService = roleService;
        this.personDetailsService = personDetailsService;
        this.roleRepo = roleRepo;
    }

    /*Получение всех пользователей*/
    @GetMapping("/people")
    public List<Person> peopleInfo() {
        return personDetailsService.allPersons();
    }

    /*Получение конкретного пользователя по id*/
    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable long id) {
        Person person = personDetailsService.getPersonId(id);
        if (person == null) throw new NoSuchPersonException("Person with id= " + id + " was not found");
        return person;
    }

    /*Удаление пользователя по id*/
    @DeleteMapping("/person/{id}")
    public String delete(@PathVariable long id) {
        Person person = personDetailsService.getPersonId(id);
        if (person == null) throw new NoSuchPersonException("Person not Found");
        personDetailsService.remove(id);

        return "Person was deleted";
    }

    /*Добавление нового пользователя*/
    @PostMapping("/people")
    public String addNewPerson(@RequestBody Person person) {
        personDetailsService.savePerson(person);
        return "Person was added";
    }

    /*Обновление пользователя*/
    @PutMapping("/people")
    public String updatePerson(@RequestBody Person person) {
        personDetailsService.savePerson(person);
        return "Person was updated";
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionInformation> handler(NoSuchPersonException exception) {
        ExceptionInformation data = new ExceptionInformation();
        data.setInform(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    //
    @GetMapping("/authentic")
    public ResponseEntity<Person> getAuthent(@AuthenticationPrincipal Person person) {
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

