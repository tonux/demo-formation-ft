package com.sn.finetech.finetechapp.services.impl;

import com.sn.finetech.finetechapp.model.Department;
import com.sn.finetech.finetechapp.model.Person;
import com.sn.finetech.finetechapp.repositories.DepartmentRepository;
import com.sn.finetech.finetechapp.services.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department create(Department department) {
        return this.departmentRepository.save(department);
    }

    @Override
    public List<Department> createAll(Iterable<Department> departements) {
        return this.departmentRepository.saveAll(departements);
    }

    @Override
    public Optional<List<Department>> findByName(String name) {
        return this.departmentRepository.findByName(name);
    }

    @Override
    public Optional<List<Department>> findByCode(String code) {
        return this.departmentRepository.findByCode(code);
    }

    @Override
    public Optional<Department> findById(Long id) {
        return this.departmentRepository.findById(id);
    }

    @Override
    public Optional<List<Department>> findAll() {
        return Optional.of(this.departmentRepository.findAll());
    }

    @Override
    public Optional<List<Person>> findAllHabitants(Long id) {
        return Optional.of(this.departmentRepository.findPersonsByDepartmentId(id));
    }

    @Override
    public void update(Department department) {
        if(departmentRepository.findById(department.getId()).isEmpty())
            return;
        departmentRepository.save(department);
    }
}
