package com.sn.finetech.finetechapp.controllers.api;


import com.sn.finetech.finetechapp.exception.ApiException;
import com.sn.finetech.finetechapp.model.Person;
import com.sn.finetech.finetechapp.model.Role;
import com.sn.finetech.finetechapp.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class ApiPersonController {

    /**
     * - GET /api/v1/person
     * - POST /api/v1/person
     * - GET /api/v1/person/{id}
     * - PATCH /api/v1/person/{id}
     * - PUT /api/v1/person/{id}
     * - DELETE /api/v1/person/{id}
     * - GET /api/v1/person/search?lastName="Mane"
     */

    private final PersonService personService;

    public ApiPersonController(PersonService personService) {
        this.personService = personService;
    }

    // create person
    @PostMapping
    @PreAuthorize("hasAuthority('"+ Role.ADMIN+"')")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person personResponse = personService.createPerson(person);
        if(personResponse == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Person not created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(personResponse);
    }

    // find all persons
    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }

    // find person by id
    @GetMapping("/{id}")
    public Person findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @GetMapping("/search")
    public Person findByName(@RequestParam(name="lastName") String lastName) {
        return personService.findByLastName(lastName);
    }

    // find person by first name
    @GetMapping("/firstName")
    public List<Person> findByFirstName(@RequestParam(name="firstName") String firstName) {
        return personService.findByFirstName(firstName);
    }

    // find person by first name and last name
    @GetMapping("/firstNameAndLastName")
    public List<Person> findByFirstNameAndLastName(@RequestParam(name="firstName") String firstName, @RequestParam(name="lastName") String lastName) {
        return personService.findByFirstNameAndLastName(firstName, lastName);
    }

    // delete person by id
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }

    // update person
    @PatchMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }
}
