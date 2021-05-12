package com.cg.java.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightRepository extends MongoRepository<Flight, Integer> {

}
