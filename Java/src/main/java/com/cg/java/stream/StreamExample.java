package com.cg.java.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.cg.java.dto.Employee;

public class StreamExample {

	public static void main(String[] args) {

		// Creating Stream
		Stream<String> s1 = Stream.of("A", "B", "C");
		s1.forEach(System.out::println);

		List<String> list = Arrays.asList("D", "E", "F"); // We can create stream from any Collection like list,set,...
		Stream<String> s2 = list.stream();
		s2.forEach(s -> System.out.println(s));

		// Filter
		List<Employee> employees = Employee.getEmployees().stream().filter((employee) -> employee.getSalary() > 1000)
				.collect(Collectors.toList());
		employees.forEach(System.out::println);

		// Sorting for String,int,..
		List<String> fruits = Arrays.asList("Apple", "Mango", "Banana");
		fruits.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println); // Ascending
		fruits.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println); // Descemding

		// Sorting Objects, we need to specify field for sorting
		Employee.getEmployees().stream().sorted((e1, e2) -> e1.getSalary() - e2.getSalary()); // Using lambdas for Objects

		// Sort by name in ascending and salary in descending
		Employee.getEmployees().stream()
				.sorted(Comparator.comparing(Employee::getName).reversed()
						.thenComparing(Comparator.comparing(Employee::getSalary).reversed()))
				.forEach(System.out::println);

		// Map
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		numbers.stream().map((n) -> "Double is " + n * 2).forEach(System.out::println);

		// Statistics
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("Highest number in List : " + stats.getMax());
		System.out.println("Lowest number in List : " + stats.getMin());
		System.out.println("Sum of all numbers : " + stats.getSum());
		System.out.println("Average of all numbers : " + stats.getAverage());
		
		//Terminal operations

	}

}
