package com.hackerrank.github.repository;

import org.springframework.data.repository.CrudRepository;

import com.hackerrank.github.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
	
	
}
