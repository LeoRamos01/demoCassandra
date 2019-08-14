package com.example.demoCassandra;

import org.springframework.data.repository.CrudRepository;

public interface PeopleRepository extends CrudRepository<Person, Integer> {
	
}
