package com.sn.finetech.finetechapp.services;

import com.sn.finetech.finetechapp.model.Person;

import java.time.LocalDate;
import java.util.List;

public interface PersonService {

    List<Person> findAll();
    Person createPerson(Person person);
    List<Person> findByFirstName(String firstName);
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findByBirthdayGreaterThan(LocalDate birthday);
    List<Person> findByQuery(String firstName, String lastName);
    List<Person> findByDepartmentCodeContainsI();
    Person findById(Long id);
    void deletePerson(Long id);
    List<Person> findByLastName(String lastName);

    void update(Person person);
}
