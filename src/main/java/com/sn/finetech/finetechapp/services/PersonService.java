package com.sn.finetech.finetechapp.services;

import com.sn.finetech.finetechapp.model.Person;

import java.time.LocalDate;
import java.util.List;

public interface PersonService {

    List<Person> findAll();

    Person createPerson(Person person);
    public List<Person> findByFirstName(String firstName);
    public List<Person> findByFirstNameAndLastName(String firstName, String lastName);
    public List<Person> findByBirthdayGreaterThan(LocalDate birthday);
    public List<Person> findByQuery(String firstName, String lastName);
    public List<Person> findByDepartmentCodeContainsI();

    Person findById(Long id);

    void deletePerson(Long id);

    Person findByLastName(String lastName);

    Person updatePerson(Long id, Person person);
}
