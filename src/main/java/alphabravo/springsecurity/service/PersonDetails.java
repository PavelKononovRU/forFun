package alphabravo.springsecurity.service;

import alphabravo.springsecurity.model.Person;

import java.util.List;


public interface PersonDetails {
    void savePerson(Person person);

    void toUpdatePerson(long id, Person updatedPerson);

    void remove(long id);

    Person getPersonId(long id);

    List<Person> allPersons();
}
