package com.hackerrank.github.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.github.repository.ActorRepository;

@Service
public class ActorServiceImpl  {
	
	@Autowired
	private ActorRepository actorRepository;	
	
}
