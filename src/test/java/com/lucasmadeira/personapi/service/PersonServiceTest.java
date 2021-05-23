package com.lucasmadeira.personapi.service;

import com.lucasmadeira.personapi.dto.request.PersonDTO;
import com.lucasmadeira.personapi.dto.response.MessageResponseDTO;
import com.lucasmadeira.personapi.entity.Person;
import com.lucasmadeira.personapi.mapper.PersonMapper;
import com.lucasmadeira.personapi.repository.PersonRepository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static com.lucasmadeira.personapi.utils.PersonUtils.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	@Mock
	private PersonMapper personMapper;

	@InjectMocks
	private PersonService personService;



	@Test
	void testGivenPersonDTOThenReturnSuccessSavedMessage() {
		PersonDTO personDTO = createFakeDTO();
		Person expectedSavedPerson = createFakeEntity();

		//when(personMapper.toModel(personDTO)).thenReturn(expectedSavedPerson);
		when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

		MessageResponseDTO expectedSuccessMessage = createExpectedSuccessMessage(expectedSavedPerson.getId());
		MessageResponseDTO successMessage = personService.createPerson(personDTO);

		assertEquals(expectedSuccessMessage, successMessage);
	}

	private MessageResponseDTO createExpectedSuccessMessage(Long savedPersonId) {
		return MessageResponseDTO.builder()
				.message("Person successfully created with ID " + savedPersonId)
				.build();
	}


}
