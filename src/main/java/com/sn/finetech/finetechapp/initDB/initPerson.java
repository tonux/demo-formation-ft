package com.sn.finetech.finetechapp.initDB;

import com.sn.finetech.finetechapp.model.Department;
import com.sn.finetech.finetechapp.model.Person;
import com.sn.finetech.finetechapp.repositories.DepartmentRepository;
import com.sn.finetech.finetechapp.repositories.PersonRepository;
import com.sn.finetech.finetechapp.services.DepartmentService;
import com.sn.finetech.finetechapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class initPerson implements ApplicationListener<ApplicationReadyEvent> {

    PersonService personService;

    DepartmentService departmentService;

    public initPerson(PersonService personService, DepartmentService departmentService) {
        this.personService = personService;
        this.departmentService = departmentService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("Application ready");
/*
        // create Person with lastName, firstName, email, birthday
        Person person = new Person("Doe", "John", "john@gmail.com", LocalDate.of(1980, 3, 12));
        Person person2 = new Person("Doe", "Jane", "Jane@gmail.com", LocalDate.of(1980, 3, 12));
        Department department = new Department("IT department", "IT department description", "IT");
        Department departmentRH = new Department("RH department", "RH department description", "RH");
        Department departmentMKG = new Department("MKG department", "MKG department description", "MKG");

        departmentService.createDepartment(department);
        departmentService.createDepartment(departmentRH);
        departmentService.createDepartment(departmentMKG);

        person.setDepartment(department);
        person2.setDepartment(departmentRH);
        personService.createPerson(person);
        personService.createPerson(person2);

 */

        /*
        personRepository.findById(1L).ifPresent(System.out::println);

        personRepository.findByFirstName("Jane").forEach(System.out::println);

        person.setId(1L);
        personRepository.delete(person);

        personRepository.findById(1L).ifPresent(System.out::println);

        personRepository.findByDepartmentCodeContainsI().forEach(System.out::println);


         */
    }
}
