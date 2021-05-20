package com.cg.java.optional;

import java.util.Optional;

import com.cg.java.dto.Computer;
import com.cg.java.dto.Soundcard;

public class OptionalExample {

	// References
	// https://www.oracle.com/technical-resources/articles/java/java8-optional.html
	// https://medium.com/@lprakashv/handling-nulls-in-nested-objects-java-7079b9413ec9

	public static void main(String[] args) {

		System.out.println("null == null: " + (null == null));

		Computer computer = new Computer();
//		String version = computer.getSoundcard().getUsb().getVersion();
//		System.out.println(version);

		// Creating optional
		Optional.of(computer); // If computer is null, throws NullPointerException
		Optional.ofNullable(null); // Creates empty optional

		Optional<String> ver = Optional.ofNullable(computer).map(c -> c.getSoundcard()).map(s -> s.getUsb())
				.map(u -> u.getVersion());
		// System.out.println(ver.get()); //Throws NoSuchElementException if any object
		// in the nested call is null
		// System.out.println(ver); //Empty optional, does not throw exception

		if (ver.isPresent()) {
			System.out.println(ver.get());
		}

		// https://ducmanhphan.github.io/2019-12-06-Best-practice-for-Optional-in-Java/
		ver.ifPresent(value -> System.out.println(value));

		// Default value
		String defaultVer = Optional.ofNullable(computer).map(c -> c.getSoundcard()).map(s -> s.getUsb())
				.map(u -> u.getVersion()).orElse("Default value");
		System.out.println(defaultVer);

		// Throwing exception
		try {
			Optional.ofNullable(computer).map(c -> c.getSoundcard()).map(s -> s.getUsb()).map(u -> u.getVersion())
					.orElseThrow(() -> new NullPointerException("Throwing exception"));
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		// We can also control failover at each stage
		Optional<String> version = Optional.ofNullable(computer)
				.map(c -> Optional.ofNullable(c.getSoundcard()).orElse(new Soundcard())).map(s -> s.getUsb())
				.map(u -> u.getVersion());
		System.out.println(version);

		// Flatmap - flattens nested optional
		// Here getName() returns optional, so map(n -> n.getName()) will return
		// Optional<Optional<String>>
		// flatMap(n -> n.getName()) returns Optional<String>
		Optional<String> s = Optional.ofNullable(computer).map(c -> c.getSoundcard()).flatMap(n -> n.getName());
		System.out.println(s);

		// Filter
		computer.setSoundcard(new Soundcard());
		computer.getSoundcard().setName(Optional.of("Hello"));
		Optional.ofNullable(computer).map(c -> c.getSoundcard()).flatMap(n -> n.getName())
				.filter(str -> "Hello".equals(str)).ifPresent(System.out::println);
		
	}

}
