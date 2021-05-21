package com.cg.java.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
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
		s2.forEach(s -> {
			// Multiple statements in for each
			System.out.println(s);
			int i=1;
			i = i+1;
		});

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
		// Use Comparator.comparingInt as Comparator.comparing does autoboxing and unboxing which affects performance
		// Comparator.comparingInt is faster
		Employee.getEmployees().stream()
				.sorted(Comparator.comparing(Employee::getName)
				.thenComparing(Comparator.comparingInt(Employee::getSalary).reversed()))
				.forEach(System.out::println);

		// Map Converting(mapping) one object to other type
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		numbers.stream().map((n) -> "Double is " + n * 2).forEach(System.out::println);
		
		// Flatmap - For iterating over nested
		List<List<String>> namesNested = Arrays.asList( 
			      Arrays.asList("Jeff", "Bezos"), 
			      Arrays.asList("Bill", "Gates"), 
			      Arrays.asList("Mark", "Zuckerberg"));
		List<String> namesFlatStream = namesNested.stream().flatMap(Collection::stream)
	      .collect(Collectors.toList());
		System.out.println(namesFlatStream);  //[Jeff, Bezos, Bill, Gates, Mark, Zuckerberg]

		// Statistics
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("Highest number in List : " + stats.getMax());
		System.out.println("Lowest number in List : " + stats.getMin());
		System.out.println("Sum of all numbers : " + stats.getSum());
		System.out.println("Average of all numbers : " + stats.getAverage());
		
		// allMatch, anyMatch, and noneMatch
		List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
		boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);  // check if all element satisfy condition (a && b && ..)
	    boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);  // atleast one satisfies condition (a || b || ..)
	    boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);  // None match condition
	    System.out.println(allEven + " " + oneEven + " " + noneMultipleOfThree);
		
		// Terminal operations max,min,distinct,sum,count
	    // Find employee with max salary
	    Employee employee = Employee.getEmployees().stream().max((e1,e2) -> {
	    	return e1.getSalary() - e2.getSalary();
	    }).orElse(null);
	    
	    // We can map empolyee and convert it to int and then return int
	    int max = Employee.getEmployees().stream().map(e -> e.getSalary()).max((a,b) -> {
	    	return a - b;
	    }).orElse(null);
	    System.out.println(employee + " " + max);
	    
	    // Count employees having salary > 1000
	    Employee.getEmployees().stream().filter(e -> e.getSalary() > 1000).count();
	    
	    // Reduce
	    OptionalInt opt = Employee.getEmployees().stream()
				.mapToInt(e -> e.getSalary())
				.reduce((subtotal,element)->subtotal+element);
	    opt.ifPresent(System.out::println);
	    
	    int reduce = Employee.getEmployees().stream()
	    		.map(e -> e.getSalary())
	    		.reduce(0,(subtotal,element)->subtotal+element);
	    System.out.println(reduce);
	    
	    reduce = Employee.getEmployees().stream()
	    		.map(e -> e.getSalary())
	    		.reduce(0,Integer::sum,Integer::sum);
	    System.out.println(reduce);

	}

}
