package org.springframework.samples.petclinic.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {
	private String email;
	private String password;
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime modifiedAt = LocalDateTime.now();
}
