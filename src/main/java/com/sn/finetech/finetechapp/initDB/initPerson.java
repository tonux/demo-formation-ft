package com.sn.finetech.finetechapp.initDB;

import com.sn.finetech.finetechapp.services.DepartmentService;
import com.sn.finetech.finetechapp.services.PersonService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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

        departmentService.create(department);
        departmentService.create(departmentRH);
        departmentService.create(departmentMKG);

        person.setDepartment(department);
        person2.setDepartment(departmentRH);
        personService.create(person);
        personService.create(person2);

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
