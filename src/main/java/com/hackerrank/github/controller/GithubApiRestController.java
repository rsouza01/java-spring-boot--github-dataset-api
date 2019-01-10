package com.hackerrank.github.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.github.enums.ActorsOrdering;
import com.hackerrank.github.exceptions.ActorNotFoundException;
import com.hackerrank.github.exceptions.ActorUpdateFieldsOtherThanAvatarException;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.services.ActorService;
import com.hackerrank.github.services.EventService;

@RestController
public class GithubApiRestController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private ActorService actorService;

	/**
	 * Erasing all the events: The service should be able to erase all the events by the DELETE request at /erase. 
	 * The HTTP response code should be 200.
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/erase", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllEvents(HttpServletRequest request) {

		eventService.deleteAll();
	}


	/**
	 * Adding new events: The service should be able to add a new event by the POST request at /events. 
	 * The event JSON is sent in the request body. 
	 * If an event with the same id already exists then the HTTP response code should be 400, otherwise, the response code should be 201.
	 * @param request
	 */
	@RequestMapping(value = "/events", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Long> addNewEvent(@RequestBody Event event, HttpServletRequest request) {

		Long eventId = eventService.addNewEvent(event);
		
		return new ResponseEntity<Long>(eventId, HttpStatus.OK);	
	}
	
	/**
	 * Returning all the events: The service should be able to return the JSON array of all the events by the GET request at /events. 
	 * The HTTP response code should be 200. The JSON array should be sorted in ascending order by event ID.
	 *  
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getAllEvents(HttpServletRequest request) {

		List<Event> events = eventService.getAllEvents();
		
		return new ResponseEntity<List<Event>>(events, HttpStatus.OK);	
	}

	/**
	 * Returning the event records filtered by the actor ID: The service should be able to return the JSON array of all the events 
	 * which are performed by the actor ID by the GET request at /events/actors/{actorID}. 
	 * If the requested actor does not exist then HTTP response code should be 404, otherwise, 
	 * the response code should be 200. The JSON array should be sorted in ascending order by event ID.
	 * Updating the avatar URL of the actor: The service should be able to update the avatar URL of the actor by the PUT request at /actors. 
	 * The actor JSON is sent in the request body. If the actor with the id does not exist then the response code should be 404, or 
	 * if there are other fields being updated for the actor then the HTTP response code should be 400, otherwise, the response code should be 200.
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/events/actors/{actorID}", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getAllEventsByActorId(@PathVariable("actorID") long actorID, HttpServletRequest request) {

		List<Event> events = eventService.getAllEvents(actorID);
		
		return new ResponseEntity<List<Event>>( events, HttpStatus.OK);	
	}
	
	/**
	 * Updating the avatar URL of the actor: The service should be able to update the avatar URL of the actor by the PUT request at /actors. 
	 * The actor JSON is sent in the request body. If the actor with the id does not exist then the response code should be 404, or if there are other 
	 * fields being updated for the actor then the HTTP response code should be 400, otherwise, the response code should be 200.
	 * @param actor
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "/actors", method = RequestMethod.PUT)
	public ResponseEntity<Long> updateActorAvatar(@RequestBody Actor actor, HttpServletRequest request) {

		try {
			
			actorService.updateActorAvatar(actor);
			
			return new ResponseEntity<Long>( -1L, HttpStatus.OK);

		} catch (ActorNotFoundException e) {
			return new ResponseEntity<Long>( -1L, HttpStatus.NOT_FOUND);
		} catch (ActorUpdateFieldsOtherThanAvatarException e) {
			return new ResponseEntity<Long>( -1L, HttpStatus.BAD_REQUEST);
		}
	}

	
	/**
	 * Returning the actor records ordered by the total number of events: The service should be able to return the JSON array of all the actors sorted 
	 * by the total number of associated events with each actor in descending order by the GET request at /actors. 
	 * If there are more than one actors with the same number of events, then order them by the timestamp of the 
	 * latest event in the descending order. If more than one actors have the same timestamp for the latest event, 
	 * then order them by the alphabetical order of login. The HTTP response code should be 200.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/actors", method = RequestMethod.GET)
	public ResponseEntity<List<Actor>> getAllActors(HttpServletRequest request) {

		List<Actor> actors = actorService.getAllActors(ActorsOrdering.NUMBER_OF_EVENTS);
		
		return new ResponseEntity<List<Actor>>(actors, HttpStatus.OK);	
	}

	
	/**
	 * Returning the actor records ordered by the maximum streak: The service should be able to return the JSON array of all the actors 
	 * sorted by the maximum streak (i.e., the total number of consecutive days actor has pushed an event to the system) 
	 * in descending order by the GET request at /actors/streak. If there are more than one actors with the same maximum streak, 
	 * then order them by the timestamp of the latest event in the descending order. 
	 * If more than one actors have the same timestamp for the latest event, then order them by the alphabetical order of login. 
	 * The HTTP response code should be 200.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/actors/streak", method = RequestMethod.GET)
	public ResponseEntity<List<Actor>> getAllActorsOrderByStreak(HttpServletRequest request) {

		List<Actor> actors = actorService.getAllActors(ActorsOrdering.MAXIMUM_STREAK);
		
		return new ResponseEntity<List<Actor>>(actors, HttpStatus.OK);	
	}

}
