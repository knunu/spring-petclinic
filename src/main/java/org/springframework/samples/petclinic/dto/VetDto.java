package org.springframework.samples.petclinic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VetDto {
	private String firstName;
	private String lastName;
	private List<String> specialtyNames;
}
