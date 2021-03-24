package com.cg.java.optional;

import java.util.Optional;

import com.cg.java.dto.Computer;
import com.cg.java.dto.Soundcard;

public class OptionalExample {
	
	//References
	//https://www.oracle.com/technical-resources/articles/java/java8-optional.html
	//https://medium.com/@lprakashv/handling-nulls-in-nested-objects-java-7079b9413ec9
	
	public static void main(String[] args) {
		
		Computer computer = new Computer();
//		String version = computer.getSoundcard().getUsb().getVersion();
//		System.out.println(version);
		
		Optional<String> ver = Optional.ofNullable(computer).map(c -> c.getSoundcard()).map(s -> s.getUsb()).map(u -> u.getVersion());
		//System.out.println(ver.get());  //Throws NoSuchElementException
		if(ver.isPresent()) {
			System.out.println(ver.get());
		}
		
		//Default value
		String defaultVer = Optional.ofNullable(computer).map(c -> c.getSoundcard()).map(s -> s.getUsb()).map(u -> u.getVersion()).orElse("Default");
		System.out.println(defaultVer);
		
		
		//We can also control failover at each stage
		Optional<String> version = Optional.ofNullable(computer).map(c -> Optional.ofNullable(c.getSoundcard()).orElse(new Soundcard())).map(s -> s.getUsb()).map(u -> u.getVersion());
		System.out.println(version);
		
	}

}
