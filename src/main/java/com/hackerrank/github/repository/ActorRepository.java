package com.hackerrank.github.repository;

import org.springframework.data.repository.CrudRepository;

import com.hackerrank.github.model.Actor;

public interface ActorRepository extends CrudRepository<Actor, Long> {
	
	
}
