package com.sn.finetech.finetechapp.controllers;

import com.sn.finetech.finetechapp.model.Person;
import com.sn.finetech.finetechapp.repositories.PersonRepository;
import com.sn.finetech.finetechapp.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String showPerson(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "index";
    }

    @GetMapping("/newPerson")
    public String newPerson(Person person) {
        return "new-person";
    }

    @PostMapping("/addPerson")
    public String addPerson(Person person){
        personService.createPerson(person);
        return "redirect:/";
    }

    @GetMapping("edit/{id}")
    public String editPerson(Model model, Long id){
        model.addAttribute("person", personService.findById(id));
        return "edit-person";
    }

    @PostMapping("/updatePerson")
    public String updatePerson(Person person){
        personService.createPerson(person);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(Long id){
        personService.deletePerson(id);
        return "redirect:/";
    }
}
