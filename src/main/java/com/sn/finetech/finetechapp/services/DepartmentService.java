package com.sn.finetech.finetechapp.services;

import com.sn.finetech.finetechapp.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> findAllDepartment();

    void createDepartment(Department department);

    Optional<Department> findDepartmentBy(Long departmentId);

    void deleteDepartmentById(Long depId);

    void findByCode(String code);
}
