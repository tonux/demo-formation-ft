package com.sn.finetech.finetechapp.repositories;

import com.sn.finetech.finetechapp.model.Department;
import com.sn.finetech.finetechapp.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepartmentRepositoryTest {

    @Mock
    DepartmentRepository departmentRepository;

    @BeforeEach
    void init(){
        //create list of departments
        List<Department> departments = List.of(
                new Department("IT Department", "Information Technology", "IT"),
                new Department("HR Department", "Human Resources", "HR"),
                new Department("FIN Department", "Finance", "FIN"),
                new Department("SALES Department", "Sales", "SALES"));

        when(departmentRepository.findByName(anyString()))
                .thenReturn(Optional.of(departments));

        when(departmentRepository.findByCode(anyString()))
                .thenReturn(Optional.of(departments));

        when(departmentRepository.findById(anyLong()))
                .thenReturn(Optional.of(departments.get(0)));

        when(departmentRepository.findAll())
                .thenReturn(departments);

        when(departmentRepository.findPersonsByDepartmentId(anyLong()))
                .thenReturn(List.of());

        when(departmentRepository.findPersonsByDepartmentId(anyLong()))
                .thenReturn(List.of(
                        new Person("lamine", "lamine", "lamine@gmail.com", LocalDate.now()),
                        new Person("gallo", "gallo", "gallo@gmail.com", LocalDate.now())
                ));

        when(departmentRepository.findById(anyLong()))
                .thenReturn(Optional.of(departments.get(0)));
    }

    @Test
    void findByName() {
        Optional<List<Department>> departments = departmentRepository.findByName("IT Department");
        assertNotNull(departments);
        assertEquals(4, departments.get().size());
        assertEquals("FIN Department", departments.get().get(2).getName());
    }

    @Test
    void findByCode() {
        Optional<List<Department>> departments = departmentRepository.findByCode("IT");
        assertNotNull(departments);
        assertEquals(4, departments.get().size());
        assertEquals("IT Department", departments.get().get(0).getName());
    }

    @Test
    void findById() {
        Optional<Department> department = departmentRepository.findById(1L);
        assertNotNull(department);
        assertEquals("IT Department", department.get().getName());
        assertEquals("Information Technology", department.get().getDescription());
    }

    @Test
    void findAll() {
        List<Department> departments = departmentRepository.findAll();
        assertNotNull(departments);
        assertEquals(4, departments.size());
        assertEquals("IT Department", departments.get(0).getName());
    }

    @Test
    void findPersonsByDepartmentId() {
        List<Person> persons = departmentRepository.findPersonsByDepartmentId(1L);
        assertNotNull(persons);
        assertEquals(2, persons.size());
        assertEquals("lamine", persons.get(0).getFirstName());
        assertEquals("gallo", persons.get(1).getFirstName());
    }
}
