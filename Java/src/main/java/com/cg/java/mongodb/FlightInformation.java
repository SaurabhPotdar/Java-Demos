package com.cg.java.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("flights")  //create table(collection) called flights
public class FlightInformation {
	
	@Id
	private String id;
	
	@Field("departure")  //giving custom column name
	@Indexed
	private String departureCity;
	
	@Transient
	private String dontSaveInDb;

}
