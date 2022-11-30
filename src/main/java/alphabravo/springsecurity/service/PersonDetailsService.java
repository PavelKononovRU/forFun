package alphabravo.springsecurity.service;

import alphabravo.springsecurity.model.Person;
import alphabravo.springsecurity.model.Role;
import alphabravo.springsecurity.repositories.PersonRepo;
import alphabravo.springsecurity.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepo personRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public PersonDetailsService(PersonRepo personRepo, RoleRepo roleRepo) {
        this.personRepo = personRepo;
        this.roleRepo = roleRepo;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepo.findPersonByUsername(username);
        if (person == null) throw new UsernameNotFoundException("Incorrect Person");
        System.out.println("Логин: " + person.getUsername() + " Пароль " + person.getPassword());
        System.out.println(person.getPassword().length());
        return person;
    }

    @Transactional
    public List<Person> allPeople() {
        return personRepo.findAll();
    }

    public List<Role> allRoles() {
        List <Role> x = roleRepo.findAll();
        for (Role y: x) {
            y.getRole();
        }
        return x;
    }
}
