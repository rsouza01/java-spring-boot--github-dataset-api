package com.hackerrank.github.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.github.repository.EventRepository;

@Service
public class EventServiceImpl {

	@Autowired
	private EventRepository eventRepository;	

	
}
