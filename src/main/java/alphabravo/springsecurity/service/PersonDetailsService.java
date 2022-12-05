package alphabravo.springsecurity.service;

import alphabravo.springsecurity.model.Person;
import alphabravo.springsecurity.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonDetailsService implements UserDetailsService, PersonDetails {
    private final PersonRepo personRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonDetailsService(PersonRepo personRepo, @Lazy PasswordEncoder passwordEncoder) {
        this.personRepo = personRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepo.findPersonByUsername(username);
        if (person == null) throw new UsernameNotFoundException("Incorrect Person");
        return person;
    }

    @Override
    public void toUpdatePerson(long id, Person updatedPerson) {
        Person personForUpdate = personRepo.findById(id).orElse(new Person());
        personForUpdate.setId(updatedPerson.getId());
        personForUpdate.setName(updatedPerson.getName());
        personForUpdate.setSurname(updatedPerson.getSurname());
        personForUpdate.setEmail(updatedPerson.getEmail());
        personForUpdate.setAge(updatedPerson.getAge());
        personRepo.save(personForUpdate);
    }

    @Override
    @Transactional
    public void savePerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepo.save(person);
    }

    @Override
    @Transactional
    public void remove(long id) {
        personRepo.deleteById(id);
    }

    @Override
    public Person getPersonId(long id) {
        return personRepo.findById(id).orElse(new Person());
    }

    @Override
    public List<Person> allPersons() {
        return personRepo.findAll().stream().toList();
    }
}
