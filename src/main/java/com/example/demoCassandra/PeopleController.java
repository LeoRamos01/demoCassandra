package com.example.demoCassandra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {

	@Autowired
	PeopleRepository peopleRepository;

	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck() {
		return "{ \"isWorking\" : true }";
	}

	@GetMapping("/people")
	public List<Person> getPeople() {
		Iterable<Person> result = peopleRepository.findAll();
		List<Person> people = new ArrayList<>();
		result.forEach(people::add);
		return people;
	}

	@GetMapping("person/{id}")
	public Optional<Person> getPerson(@PathVariable Integer id) {
		Optional<Person> emp = peopleRepository.findById(id);
		return emp;
	}

	@PutMapping("/person/{id}")
	public Optional<Person> updatePerson(@RequestBody Person newPerson, @PathVariable Integer id) {
		Optional<Person> optionalPerson = peopleRepository.findById(id);
		if (optionalPerson.isPresent()) {
			Person people = optionalPerson.get();
			people.setFullname(newPerson.getFullname());
			people.setAge(newPerson.getAge());
			peopleRepository.save(people);
		}
		return optionalPerson;
	}

	@DeleteMapping(value = "/person/{id}", produces = "application/json; charset=utf-8")
	public String deletePerson(@PathVariable Integer id) {
		Boolean result = peopleRepository.existsById(id);
		peopleRepository.deleteById(id);
		return "{ \"success\" : " + (result ? "true" : "false") + " }";
	}

	@PostMapping("/person")
	public Person addPerson(@RequestBody Person newPerson) {
		Integer id = new Random().nextInt();
		Person person = new Person(id, newPerson.getFullname(), newPerson.getAge());
		Person persisted = peopleRepository.save(person);
		return persisted;
	}

}
