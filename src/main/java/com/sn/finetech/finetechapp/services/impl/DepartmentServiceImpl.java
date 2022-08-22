package com.sn.finetech.finetechapp.services.impl;

import com.sn.finetech.finetechapp.model.Department;
import com.sn.finetech.finetechapp.repositories.DepartmentRepository;
import com.sn.finetech.finetechapp.services.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public void createDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public Optional<Department> findDepartmentBy(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Override
    public void deleteDepartmentById(Long depId) {
        departmentRepository.deleteById(depId);
    }

}
