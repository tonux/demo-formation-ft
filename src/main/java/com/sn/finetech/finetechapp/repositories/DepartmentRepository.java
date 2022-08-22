package com.sn.finetech.finetechapp.repositories;

import com.sn.finetech.finetechapp.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
