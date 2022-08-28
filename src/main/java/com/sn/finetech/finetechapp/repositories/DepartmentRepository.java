package com.sn.finetech.finetechapp.repositories;

import com.sn.finetech.finetechapp.model.Department;
import com.sn.finetech.finetechapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<List<Department>> findByName(String name);
    Optional<List<Department>> findByCode(String code);
    Optional<Department> findById(Long id);
    List<Department> findAll();
    @Query("SELECT p FROM Person p WHERE p.department.id = ?1")
    List<Person> findPersonsByDepartmentId(Long id);
}
