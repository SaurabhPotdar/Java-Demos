package com.cg.java.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.cg.java.dto.Employee;

@FunctionalInterface
interface Drawable {
	public void draw();
}

@FunctionalInterface
// Interface should have single abstract method, otherwise compiler throws an error.
interface Arithmetic {
	public int operations(int a, int b);
}

public class LamdaExpressions {

	// References
	// https://www.youtube.com/watch?v=_TFVYmumREg&list=PLGRDMO4rOGcNXD2v76zM1nJybIwhBSwzZ&index=2

	public static void main(String[] args) {

		// 1. Create Impl class for interface and implement methods
		// But the impl class will have fixed implementation, we will have to create another
		// impl for different implementation. e.g filter method
		// Using lambdas, we can any implementataion for filter method

		// 2. Anonymous class
		Drawable drawable = new Drawable() {
			@Override
			public void draw() {
				System.out.println("Drawing");
			}
		};
		drawable.draw();
		
		// 3. Lambda
		Drawable drawable2 = () -> System.out.println("Drawing");
		drawable2.draw();

		// Lambda with input params and return statement
		// We can create different objects like addable, subtractable etc with diff.
		// implementations
		Arithmetic addable = (a, b) -> a + b;
		System.out.println(addable.operations(1, 2));

		// Lambda with multiple statements
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

		List<Employee> list = Employee.getEmployees();

		// Sort on salary using anonymous class
		Collections.sort(list, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getSalary() - o2.getSalary(); // Ascending
				// return o1.getSalary() - o2.getSalary(); //Descending
			}
		});

		// Sort on salary using lambdas
		Collections.sort(list, (o1, o2) -> o1.getSalary() - o2.getSalary()); // Ascending

		// Sort on name, salary
		list.sort((o1, o2) -> {
			if (o1.getName().compareTo(o2.getName()) == 0) {
				// Same name, use salary to sort
				return o1.getSalary() - o2.getSalary();
			}
			return o1.getName().compareTo(o2.getName());
		});

	}

}
