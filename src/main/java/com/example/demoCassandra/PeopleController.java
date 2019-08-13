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

	@GetMapping("/peoples")
	public List<People> getPeoples() {
		Iterable<People> result = peopleRepository.findAll();
		List<People> peoples = new ArrayList<People>();
		result.forEach(peoples::add);
		return peoples;
	}

	@GetMapping("/people/{id}")
	public Optional<People> getPeople(@PathVariable Integer id) {
		Optional<People> emp = peopleRepository.findById(id);
		return emp;
	}

	@PutMapping("/people/{id}")
	public Optional<People> updatePeople(@RequestBody People newPeople, @PathVariable Integer id) {
		Optional<People> optionalPeople = peopleRepository.findById(id);
		if (optionalPeople.isPresent()) {
			People people = optionalPeople.get();
			people.setFullname(newPeople.getFullname());
			people.setAge(newPeople.getAge());
			peopleRepository.save(people);
		}
		return optionalPeople;
	}

	@DeleteMapping(value = "/people/{id}", produces = "application/json; charset=utf-8")
	public String deletePeople(@PathVariable Integer id) {
		Boolean result = peopleRepository.existsById(id);
		peopleRepository.deleteById(id);
		return "{ \"success\" : " + (result ? "true" : "false") + " }";
	}

	@PostMapping("/people")
	public People addPeople(@RequestBody People newPeople) {
		Integer id = new Random().nextInt();
		People pessoa = new People(id, newPeople.getFullname(), newPeople.getAge());
		peopleRepository.save(pessoa);
		return pessoa;
	}

}
