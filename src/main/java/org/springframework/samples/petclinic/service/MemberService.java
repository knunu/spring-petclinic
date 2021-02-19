package org.springframework.samples.petclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.dto.SignupDto;
import org.springframework.samples.petclinic.model.member.Member;
import org.springframework.samples.petclinic.model.member.MemberRepository;
import org.springframework.samples.petclinic.model.member.MemberRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void joinMember(SignupDto memberDto) {
		Member member = new Member();
		member.setEmail(memberDto.getEmail());
		member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
		member.setFirstName(memberDto.getFirstName());
		member.setLastName(memberDto.getLastName());

		Set<MemberRole> roles = new HashSet<>();

		if (memberDto.isAdmin()) {
			MemberRole role = new MemberRole();
			role.setMember(member);
			role.setRole(MemberRole.Role.ADMIN);
			roles.add(role);
		}

		if (memberDto.isVet()) {
			MemberRole role = new MemberRole();
			role.setMember(member);
			role.setRole(MemberRole.Role.VET);
			roles.add(role);
		}

		member.setRoles(roles);
		memberRepository.save(member);
	}
}
