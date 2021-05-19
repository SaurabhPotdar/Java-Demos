package com.cg.java.dto;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Soundcard {
	
	private Usb usb;
	
	private Optional<String> name;

}
