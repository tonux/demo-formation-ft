package com.sn.finetech.finetechapp.services.impl;

import com.sn.finetech.finetechapp.model.Department;
import com.sn.finetech.finetechapp.repositories.DepartmentRepository;
import com.sn.finetech.finetechapp.services.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(Department department){
        return departmentRepository.save(department);
    }
}
