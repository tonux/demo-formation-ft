package com.sn.finetech.finetechapp.services;

import com.sn.finetech.finetechapp.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department create(Department department);

    List<Department> createAll(Iterable<Department> departements);

    Optional<List<Department>> findByName(String name);

    Optional<List<Department>> findByCode(String code);

    Optional<Department> findById(Long id);
}
