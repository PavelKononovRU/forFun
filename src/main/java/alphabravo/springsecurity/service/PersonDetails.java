package alphabravo.springsecurity.service;

import alphabravo.springsecurity.model.Person;

import java.util.List;


public interface PersonDetails {
    void savePerson(Person person);

    void remove(long id);

    Person getPersonById(long id);

    List<Person> getPeople();
}
