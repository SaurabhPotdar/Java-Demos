package com.cg.java.dto;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
	
	private int id;
	private String name;
	private int salary;
	
	public static List<Employee> getEmployees(){
		List<Employee> list = Arrays.asList(new Employee(1, "a", 1000), new Employee(2, "c", 2000),
				new Employee(3, "d", 500), new Employee(4, "b", 1500), new Employee(5, "b", 5500), new Employee(6, "c", 500));
		return list;
	}

}
