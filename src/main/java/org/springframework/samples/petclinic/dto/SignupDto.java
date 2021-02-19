package org.springframework.samples.petclinic.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SignupDto {
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private boolean admin;
	private boolean vet;
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime modifiedAt = LocalDateTime.now();
}
