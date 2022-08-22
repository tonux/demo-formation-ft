package com.sn.finetech.finetechapp.repositories;

import com.sn.finetech.finetechapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByFirstName(String firstName);
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);

    List<Person> findByBirthdayGreaterThan(LocalDate birthday);

    @Query("select p from Person p where p.firstName = ?1 and p.lastName = ?2")
    List<Person> findByQuery(String firstName, String lastName);

    // find person by department code contains "I"
    @Query("select p from Person p where p.department.code like 'I%'")
    List<Person> findByDepartmentCodeContainsI();

    List<Person> findByLastName(String lastName);

    void deleteById(Long id);
}
