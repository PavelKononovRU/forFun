package alphabravo.springsecurity.service;

import alphabravo.springsecurity.model.Person;
import alphabravo.springsecurity.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonDetailsService implements UserDetailsService {
    PersonRepo personRepo;

    @Autowired
    public PersonDetailsService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepo.findPersonByUsername(username);
        if (person == null) throw new UsernameNotFoundException("Incorrect Person");
        System.out.println("Логин: " + person.getUsername() + "Пароль " + person.getPassword());
        return person;
    }

    @Transactional
    public List<Person> allPeople() {
        return personRepo.findAll();
    }
}
