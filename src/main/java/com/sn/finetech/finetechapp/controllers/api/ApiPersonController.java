package com.sn.finetech.finetechapp.controllers.api;


import com.sn.finetech.finetechapp.model.Person;
import com.sn.finetech.finetechapp.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    PersonService personService;

    public ApiPersonController(PersonService personService) {
        this.personService = personService;
    }

    // create person
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person personResponse = personService.createPerson(person);
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

    @GetMapping("/searchbyname")
    public ResponseEntity<List<Person>> findOneByFirstName(@RequestParam(name = "firstName") String firstName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.findByFirstName(firstName));

    }

    // find person by first name and last name
    @GetMapping("searchbyfullname")
    public ResponseEntity<List<Person>> findByFirstNameAndLastName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.findByFirstNameAndLastName(firstName, lastName));
    }

    // delete person by id
    @DeleteMapping("deleteperson")
    public void deletePersonById(@RequestParam(name = "id") Long id) {
        personService.deletePerson(id);
    }

    // update person
    @PutMapping("updateperson")
    public void putPerson(@RequestParam(name = "id") Long id , @RequestBody Person person) {
        person.setId(id);
        //Calling createPerson that call the save method of PersonRepository which will update since person already exists
        personService.createPerson(person);
    }
}
