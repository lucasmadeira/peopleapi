package com.lucasmadeira.personapi.repository;

import com.lucasmadeira.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person,Long> {


}
