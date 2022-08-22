package com.sn.finetech.finetechapp.services.impl;

import com.sn.finetech.finetechapp.model.Person;
import com.sn.finetech.finetechapp.repositories.PersonRepository;
import com.sn.finetech.finetechapp.services.PersonService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person createPerson(Person person) {
        person.setMatricule(randomString(10));
        return personRepository.save(person);
    }

    @Override
    public List<Person> findByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName);
    }

    @Override
    public List<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Person> findByBirthdayGreaterThan(LocalDate birthday) {
        return personRepository.findByBirthdayGreaterThan(birthday);
    }

    @Override
    public List<Person> findByQuery(String firstName, String lastName) {
        return personRepository.findByQuery(firstName, lastName);
    }

    @Override
    public List<Person> findByDepartmentCodeContainsI() {
        return personRepository.findByDepartmentCodeContainsI();
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return personRepository.findByLastName(lastName);
    }

    @Override
    public void update(Person person) {
        if(personRepository.findById(person.getId()).isEmpty())
            return;
        personRepository.save(person);
    }

    // create random String of length n
    public static String randomString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        int length = AlphaNumericString.length();
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (length * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
