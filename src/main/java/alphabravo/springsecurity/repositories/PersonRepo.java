package alphabravo.springsecurity.repositories;

import alphabravo.springsecurity.model.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
    @EntityGraph(attributePaths = {"roles"})
    Person findPersonByUsername(String username);
}
