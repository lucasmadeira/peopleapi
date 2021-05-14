package com.lucasmadeira.personapi.service;

import com.lucasmadeira.personapi.dto.MessageResponse;
import com.lucasmadeira.personapi.entity.Person;
import com.lucasmadeira.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public MessageResponse createPerson(Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponse.
                builder().
                message("Created person with ID"+ savedPerson.getId()).
                build();
    }
}
