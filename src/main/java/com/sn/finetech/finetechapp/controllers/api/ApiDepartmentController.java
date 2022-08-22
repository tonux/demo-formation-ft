package com.sn.finetech.finetechapp.controllers.api;

import com.sn.finetech.finetechapp.model.Department;
import com.sn.finetech.finetechapp.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/department")
public class ApiDepartmentController {

    /**
     * - GET /api/v1/department
     * - POST /api/v1/department
     * - GET /api/v1/department/{id}
     * - DELETE /api/v1/department/{id}
     */
    DepartmentService departmentService;

    public ApiDepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> findAllDepartment()
    {
        return departmentService.findAllDepartment();
    }

   @PostMapping
    public void  createDepartment(@RequestBody Department department)
   {
       departmentService.createDepartment(department);
   }
   @GetMapping("/{id}")
    public Optional<Department> findDepartmentById(@PathVariable Long id)
   {
       return departmentService.findDepartmentBy(id);
   }

   @DeleteMapping(path = "/{id}")
    public void deleteDepartmentById(Long depId)
   {
       departmentService.deleteDepartmentById(depId);
   }

}

