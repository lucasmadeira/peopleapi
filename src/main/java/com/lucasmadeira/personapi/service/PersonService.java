package com.lucasmadeira.personapi.service;

import com.lucasmadeira.personapi.dto.request.PersonDTO;
import com.lucasmadeira.personapi.dto.response.MessageResponse;
import com.lucasmadeira.personapi.entity.Person;
import com.lucasmadeira.personapi.mapper.PersonMapper;
import com.lucasmadeira.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponse createPerson(PersonDTO personDTO) {

        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponse.
                builder().
                message("Created person with ID" + savedPerson.getId()).
                build();
    }
}
