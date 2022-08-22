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
     * - GET /api/v1/person/searchByLastName?lastName="Mane"
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

        List<Person> persons = personService.findAll();
        /*if(persons.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ApiException(HttpStatus.NO_CONTENT, "No persons found", ));
        }*/
        return ResponseEntity.status(HttpStatus.OK).body(persons);
    }

    // find person by id
    @GetMapping("/{id}")
    public Person findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @GetMapping("/searchByLastName")
    public ResponseEntity<List<Person>> findByLastName(@RequestParam(name="lastName") String lastName) {
        List<Person> persons = personService.findByLastName(lastName);
        return ResponseEntity.status(HttpStatus.OK).body(persons);
    }

    @GetMapping("/searchByFirstName")
    public ResponseEntity<List<Person>> findByFistName(@RequestParam(name="firstName") String firstName) {
        List<Person> persons = personService.findByFirstName(firstName);
        return ResponseEntity.status(HttpStatus.OK).body(persons);
    }

    @GetMapping("/searchByFistNameAndLastName")
    public ResponseEntity<List<Person>> findByFisrtNameAndLastName(
            @RequestParam(name="firstName") String firstName,
            @RequestParam(name="lastName") String lastName) {
        List<Person> persons = personService.findByFirstNameAndLastName(firstName, lastName);
        return ResponseEntity.status(HttpStatus.OK).body(persons);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        personService.deletePerson(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Person person) {
        person.setId(id);
        personService.update(person);
    }
}
