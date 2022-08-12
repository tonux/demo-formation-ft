package com.sn.finetech.finetechapp.services.impl;

import com.sn.finetech.finetechapp.model.Person;
import com.sn.finetech.finetechapp.repositories.PersonRepository;
import com.sn.finetech.finetechapp.services.PersonService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;

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

    // create random String of length n
    public static String randomString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
