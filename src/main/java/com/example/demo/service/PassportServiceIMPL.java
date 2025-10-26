package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PassportRepo;
import com.example.demo.repository.PersonRepo;

@Service
public class PassportServiceIMPL implements ServiceInterface {

	@Autowired
	PassportRepo passportRepo;

	@Autowired
	PersonRepo personRepo;

	@Override
	public Person savePersonData(Person p) {
		Person save = personRepo.save(p);

		return save;

	}

	@Override
	public List<Person> getAll() {
		List<Person> all = personRepo.findAll();
		return all;
	}

	@Override
	public Optional<Person> getById(Long id) {
		Optional<Person> byId = personRepo.findById(id);
		if (byId.isEmpty()) {
			return Optional.empty();
		} else {
			return byId;
		}
	}

	@Override
	public Object updateById(Person person) {
		Person save = personRepo.save(person);
		return save;
	}

	@Override
	public void deleteById(Long id) {
		personRepo.deleteById(id);
		
	}

	

}
