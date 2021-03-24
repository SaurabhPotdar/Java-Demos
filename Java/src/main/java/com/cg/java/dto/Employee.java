package com.cg.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
	
	private int id;
	private String name;
	private int salary;

}
