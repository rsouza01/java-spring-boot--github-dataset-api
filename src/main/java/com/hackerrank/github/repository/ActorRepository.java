package com.hackerrank.github.repository;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hackerrank.github.model.Actor;

public interface ActorRepository extends CrudRepository<Actor, Long> {
	
	@Query("SELECT  act.id, act.login, act.avatar_url, (select count(evt.id) from Event evt where evt.actor.id = act.id) as totalEvents FROM Actor act order by totalEvents desc")
	List<Actor> findActorsOrderByNumberEventsDesc();

	@Query("SELECT act.id, act.login, act.avatar_url FROM Actor act")
	List<Actor> findActorsOrderByMaximumStreakDesc();
}



