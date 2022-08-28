package com.sn.finetech.finetechapp.repositories;

import com.sn.finetech.finetechapp.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonRepositoryTest {

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void init() {
        //create list of persons
        List<Person> persons = List.of(
                        new Person(
                                "Moussa",
                                "Gueye",
                                "test@gmail.com",
                                LocalDate.now()),

                        new Person(
                                "Moussa",
                                "Fall",
                                "fall@gmail.com",
                                LocalDate.now()));

        when(personRepository.findByFirstName(anyString()))
                .thenReturn(persons);

        when(personRepository.findByFirstNameAndLastName(anyString(), anyString()))
                .thenReturn(List.of(persons.get(0)));

        when(personRepository.findByBirthdayGreaterThan(any(LocalDate.class)))
                .thenReturn(List.of());

        when(personRepository.findByDepartmentCodeContainsI())
                .thenReturn(List.of(persons.get(1)));

        when(personRepository.findByLastName(anyString()))
                .thenReturn(List.of(persons.get(0)));
    }

    @Test
    void findByFirstName() {
        List<Person> persons = personRepository.findByFirstName("Moussa");
        assertNotNull(persons);
        assertEquals(2, persons.size());
    }

    @Test
    void findByFirstNameAndLastName() {
        List<Person> persons = personRepository.findByFirstNameAndLastName("Moussa", "anystring");
        assertNotNull(persons);
        assertEquals(1, persons.size());
    }

    @Test
    void findByBirthdayGreaterThan() {
        List<Person> persons = personRepository.findByBirthdayGreaterThan(LocalDate.now());
        assertNotNull(persons);
        assertEquals(0, persons.size());
    }

    @Test
    void findByDepartmentCodeContainsI() {
        List<Person> persons = personRepository.findByDepartmentCodeContainsI();
        assertNotNull(persons);
        assertEquals(1, persons.size());
    }

    @Test
    void findByLastName() {
        Person person = personRepository.findByLastName("Moussa").get(0);
        assertNotNull(person);
        assertEquals("Moussa", person.getLastName());
        assertEquals("test@gmail.com", person.getEmail());
    }
}