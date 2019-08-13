package com.example.demoCassandra;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Table
public class People {

	@PrimaryKey
	@NonNull
	private Integer id;

	@NonNull
	private String fullname;

	@NonNull
	private Integer age;
	
}
