package com.sn.finetech.finetechapp.services;

import com.sn.finetech.finetechapp.model.Person;

import java.time.LocalDate;
import java.util.List;

public interface PersonService {

    Person createPerson(Person person);
    public List<Person> findByFirstName(String firstName);
    public List<Person> findByFirstNameAndLastName(String firstName, String lastName);
    public List<Person> findByBirthdayGreaterThan(LocalDate birthday);
    public List<Person> findByQuery(String firstName, String lastName);
    public List<Person> findByDepartmentCodeContainsI();
}
