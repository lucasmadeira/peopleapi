package com.lucasmadeira.personapi.service;

import com.lucasmadeira.personapi.dto.request.PersonDTO;
import com.lucasmadeira.personapi.dto.response.MessageResponse;
import com.lucasmadeira.personapi.entity.Person;
import com.lucasmadeira.personapi.exception.PersonNotFoundException;
import com.lucasmadeira.personapi.mapper.PersonMapper;
import com.lucasmadeira.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponse createPerson(PersonDTO personDTO) {

        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream().
                map(personMapper::toDTO).
                collect(Collectors.toList());

    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponse updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException{

        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(savedPerson.getId(), "Updated person with ID");

    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponse createMessageResponse(Long id, String s) {
        return MessageResponse.
                builder().
                message(s + id).
                build();
    }
}
