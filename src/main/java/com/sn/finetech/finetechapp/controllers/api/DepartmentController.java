package com.sn.finetech.finetechapp.controllers.api;

import com.sn.finetech.finetechapp.model.Department;
import com.sn.finetech.finetechapp.model.Person;
import com.sn.finetech.finetechapp.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //create department
    @PostMapping
    public ResponseEntity<Department> createDepartment(Department department){
        Department depart = departmentService.create(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(depart);
    }

    //find all
    @GetMapping
    public ResponseEntity<List<Department>> findAll(){
        Optional<List<Department>> departments = departmentService.findAll();

        return departments
                .map(departmentList -> ResponseEntity.status(HttpStatus.OK).body(departmentList))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    //find by name
    @GetMapping("searchByName")
    public ResponseEntity<List<Department>> searchByName(@RequestParam("name") String name){
        Optional<List<Department>> departments = departmentService.findByName(name);

        return departments
                .map(departmentList -> ResponseEntity.status(HttpStatus.OK).body(departmentList))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    //find by code
    @GetMapping("searchByCode/{code}")
    public ResponseEntity<List<Department>> searchByCode(@PathVariable("code") String code){
        Optional<List<Department>> departments = departmentService.findByCode(code);

        return departments
                .map(departmentList -> ResponseEntity.status(HttpStatus.OK).body(departmentList))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    //find by id
    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable("id") Long id){
        Optional<Department> department = departmentService.findById(id);

        return department
                .map(departmentList -> ResponseEntity.status(HttpStatus.OK).body(departmentList))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    //find all habitants
    @GetMapping("/{id}/habitants")
    public ResponseEntity<List<Person>> findAllHabitants(@PathVariable("id") Long id){
        Optional<List<Person>> persons = departmentService.findAllHabitants(id);

        return persons
                .map(personList -> ResponseEntity.status(HttpStatus.OK).body(personList))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

}
