package org.springframework.samples.petclinic.model.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "member_roles")
@Getter
@Setter
public class MemberRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Getter
	@RequiredArgsConstructor
	public enum Role {
		ADMIN("ROLE_ADMIN"),
		VET("ROLE_VET");

		private final String value;
	}
}
