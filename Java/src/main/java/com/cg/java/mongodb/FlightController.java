package com.cg.java.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {
	
	@Autowired
	private FlightRepository flightRepository;
	
	@PostMapping(value="")
	public ResponseEntity<?> addFlight(@RequestBody Flight flight) {
		System.out.println(flight);
		flightRepository.save(flight);
		return new ResponseEntity<>("Inserted successfully",HttpStatus.OK);
	}
	
	@GetMapping(value="")
	public ResponseEntity<?> test() {
		return new ResponseEntity<>(flightRepository.findAll(),HttpStatus.OK);
	}

}
