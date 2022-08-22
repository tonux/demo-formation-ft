package com.sn.finetech.finetechapp.repositories;

import com.sn.finetech.finetechapp.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonRepositoryTest {

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void init() {
        // personRepository.deleteAll();
        // personRepository.save(new Person("Moussa", "Moussa", "test@gmail.com", LocalDate.now()));
    }

    @Test
    void findByFirstName() {
        when(personRepository.findByFirstName(anyString()))
                .thenReturn(List.of());
        List<Person> persons = personRepository.findByFirstName("tonux");
        assertNotNull(persons);
        assertEquals(0, persons.size());
    }

    @Test
    void findByFirstNameAndLastName() {
    }

    @Test
    void findByBirthdayGreaterThan() {
    }

    @Test
    void findByQuery() {
    }

    @Test
    void findByDepartmentCodeContainsI() {
    }

    @Test
    void findByLastName() {
        when(personRepository.findByLastName(anyString()))
                .thenReturn(new Person("Moussa", "Moussa", "test@gmail.com", LocalDate.now()));
        Person person = personRepository.findByLastName("Moussa");
        assertNotNull(person);
        assertEquals("Moussa", person.getLastName());
        assertEquals("test@gmail.com", person.getEmail());
    }
}