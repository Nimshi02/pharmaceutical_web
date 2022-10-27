package com.spring.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.spring.mongodb.model.Drug;


public interface DrugRepostory extends MongoRepository<Drug, Integer>{
	
}



	