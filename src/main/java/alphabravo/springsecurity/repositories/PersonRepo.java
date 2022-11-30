package alphabravo.springsecurity.repositories;

import alphabravo.springsecurity.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
    Person findPersonByUsername(String username);
}
