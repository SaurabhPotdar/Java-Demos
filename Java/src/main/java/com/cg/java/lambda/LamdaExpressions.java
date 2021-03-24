package com.cg.java.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.cg.java.dto.Employee;

interface Drawable {
	public void draw();
}

interface Arithmetic {
	public int operations(int a, int b);
}

public class LamdaExpressions {
	
	//References
	//https://www.youtube.com/watch?v=_TFVYmumREg&list=PLGRDMO4rOGcNXD2v76zM1nJybIwhBSwzZ&index=2

	public static void main(String[] args) {

		// Anonymous class
		Drawable drawable = new Drawable() {
			@Override
			public void draw() {
				System.out.println("Drawing");
			}
		};
		drawable.draw();

		// Lambda
		Drawable drawable2 = () -> System.out.println("Drawing");
		drawable2.draw();

		// Lambda with input params and return statement
		Arithmetic addable = (a, b) -> a + b;
		System.out.println(addable.operations(1, 2));

		//Lambda with multiple statements
		addable = (a, b) -> {
			// Perform some operations
			return a - b;
		};

		// Runnable using anonymous class
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Runnable using anonymous class");
			}
		});
		thread.start();

		// Runnable using lambda
		thread = new Thread(() -> {
			System.out.println("Runnable using lambda");
		});

		List<Employee> list = Arrays.asList(new Employee(1, "a", 1000), new Employee(2, "c", 2000),
				new Employee(3, "d", 500), new Employee(4, "b", 1500));
		
		//Sort on salary using anonymous class
		Collections.sort(list, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getSalary() - o2.getSalary();  //Ascending
				//return o1.getSalary() - o2.getSalary();  //Descending
			}
		});
		
		//Sort on salary using lambdas
		Collections.sort(list, (o1,o2) -> o1.getSalary() - o2.getSalary());  //Ascending
		
		//Sort on name, salary
		list.sort((o1, o2) -> {
	        if (o1.getName().compareTo(o2.getName()) == 0) {
	        	//Same name, use salary to sort
	            return o1.getSalary() - o2.getSalary();
	        } else {
	            return o1.getName().compareTo(o2.getName());
	        } 
	    });

	}

}
