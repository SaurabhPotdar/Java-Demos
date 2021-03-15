package com.cg.java.resttemplate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/resttemplate")
public class RestTemplateParams {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<String> foo(@PathVariable String id, @RequestParam("name") String name, @RequestParam("department") String department) {
		String str = id + " " +  name + " " + department;
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
	
	/**
	 * Passing uriVariables to RestTemplate
	 * https://stackoverflow.com/questions/8297215/spring-resttemplate-get-with-parameters
	 * https://springbootdev.com/2017/11/21/spring-resttemplate-exchange-method/
	 * @return
	 */
	@GetMapping(value="")
	public ResponseEntity<String> bar() {
		Map<String,String> uriVariables = new HashMap<>();
		//http://localhost:8081/resttemplate/1?name=Shank&department=IT
		String url = "http://localhost:8081/resttemplate/{id}?name={name}&department={department}";
		uriVariables.put("id", "1");
		uriVariables.put("name", "Saurabh");
		uriVariables.put("department", "IT");
		String str = restTemplate.getForObject(url, String.class, uriVariables);
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

}
