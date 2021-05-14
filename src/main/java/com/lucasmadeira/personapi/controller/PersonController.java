package com.lucasmadeira.personapi.controller;

import com.lucasmadeira.personapi.dto.MessageResponse;
import com.lucasmadeira.personapi.entity.Person;
import com.lucasmadeira.personapi.repository.PersonRepository;
import com.lucasmadeira.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public MessageResponse createPerson(@RequestBody  Person person){
           return personService.createPerson(person);
        }

}
