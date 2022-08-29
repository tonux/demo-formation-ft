package com.sn.finetech.finetechapp.repositories;


import com.sn.finetech.finetechapp.model.Person;
import com.sn.finetech.finetechapp.services.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonRepositoryTest {

    @Mock
    PersonRepository personRepository;


    @Test
    void findByFirstName() {

        when(personRepository.findByFirstName(anyString()))
                .thenReturn(List.of());
        List<Person> personList = personRepository.findByFirstName("gallo");

        assertNotNull(personList);
        assertEquals(0, personList.size());

    }

    @Test
    void findByFirstNameAndLastName() {

        when(personRepository.findByFirstNameAndLastName(anyString(), anyString()))
                .thenReturn(List.of());
        // We know there is no row in the database with "firstName" and "lastName"
        List<Person> personListExpectedEmpty = personRepository.findByFirstNameAndLastName("firstName", "lastName");

        //So we are expecting an empty list but still a list
        assertNotNull(personListExpectedEmpty);

        //Since we use Mock we don't actually call the service
        assertEquals(0, personListExpectedEmpty.size());




    }

    @Test
    void findByBirthdayGreaterThan() {

        when(personRepository.findByBirthdayGreaterThan(any()))
                .thenReturn(List.of());

        List<Person> personList = personRepository.findByBirthdayGreaterThan(LocalDate.of(1999, Month.AUGUST, 12));

        assertTrue(personList.isEmpty());



    }

    @Test
    void findByQuery() {

        when(personRepository.findByQuery(anyString(), anyString()))
                .thenReturn(List.of());

        List<Person> personList = personRepository.findByQuery("fistName", "lastName");

        assertNotNull(personList);

        assertEquals(0, personList.size());

    }

    @Test
    void findByDepartmentCodeContainsI() {

        when(personRepository.findByDepartmentCodeContainsI())
                .thenReturn(List.of());

        List<Person> personList = personRepository.findByDepartmentCodeContainsI();

        assertNotNull(personList);

        assertTrue(personList.isEmpty());

    }

}
