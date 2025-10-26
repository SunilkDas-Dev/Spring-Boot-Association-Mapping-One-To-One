package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Person;

public interface ServiceInterface {

	public Person savePersonData(Person p);

	public List<Person> getAll();

	public Optional<Person> getById(Long id);

	public Object updateById(Person person);

	public void deleteById(Long id);

}
