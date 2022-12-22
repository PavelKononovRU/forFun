package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.model.Person;
import alphabravo.springsecurity.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class PeopleRESTController {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public PeopleRESTController(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/people")
    public ResponseEntity<List<Person>> peopleInfo() {
        List<Person> people = personDetailsService.getPeople();
        return ((people != null) && (!people.isEmpty()))
                ? new ResponseEntity<>(people, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Person person = personDetailsService.getPersonById(id);
        return (person != null)
                ? new ResponseEntity<>(person, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable Long id) {
        if (personDetailsService.getPersonById(id) == null) {
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
    public ResponseEntity<Person> updateUser(@RequestBody Person person, @PathVariable Long id) {
        if (personDetailsService.getPersonById(id) == null) {
            return new ResponseEntity<>(person, HttpStatus.NOT_FOUND);
        }
        personDetailsService.savePerson(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/authentic")
    public ResponseEntity<Person> getAuthentic(@AuthenticationPrincipal Person person) {
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}

