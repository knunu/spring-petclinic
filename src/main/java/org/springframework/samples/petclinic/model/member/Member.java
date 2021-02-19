package org.springframework.samples.petclinic.model.member;

import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.model.common.Person;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "members")
@Getter
@Setter
public class Member extends Person {
	@Column
	private String email;

	@Column
	private String password;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "member_role")
	private Set<MemberRole> roles;
}
