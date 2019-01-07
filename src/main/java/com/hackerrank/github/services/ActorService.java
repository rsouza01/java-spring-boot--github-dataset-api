package com.hackerrank.github.services;

import java.util.List;

import com.hackerrank.github.enums.ActorsOrdering;
import com.hackerrank.github.model.Actor;

public interface ActorService  {

	List<Actor> getAllActors(ActorsOrdering ordering);

}
