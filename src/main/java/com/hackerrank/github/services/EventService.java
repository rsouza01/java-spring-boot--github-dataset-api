package com.hackerrank.github.services;

import java.util.List;

import com.hackerrank.github.model.Event;

public interface EventService {
	
	void deleteAll();

	Long addNewEvent(Event event);

	List<Event> getAllEvents();

	List<Event> getAllEvents(int actorID);
}
