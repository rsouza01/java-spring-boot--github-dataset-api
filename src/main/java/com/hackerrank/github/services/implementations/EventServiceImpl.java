package com.hackerrank.github.services.implementations;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.github.model.Event;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.services.EventService;

@Service
public class EventServiceImpl extends GithubService implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Override
	public void deleteAll() {
		eventRepository.deleteAll();
	}

	@Override
	public Long addNewEvent(Event event) {
		Event newEvent = eventRepository.save(event);
		return newEvent.getId();
	}
	
	
	@Override
	public List<Event> getAllEvents() {
			
		return GithubService.toList(eventRepository.findAll());
	}

	@Override
	public List<Event> getAllEvents(int actorID) {
		// TODO Auto-generated method stub
		return null;
	}	
}
