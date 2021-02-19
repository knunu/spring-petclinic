package org.springframework.samples.petclinic.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.model.member.Member;
import org.springframework.samples.petclinic.model.member.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class MemberAuthenticator implements UserDetailsService {
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email).orElse(null);
		if (member != null) {
			List<GrantedAuthority> authorities = member.getRoles().stream()
				.map(memberRole -> new SimpleGrantedAuthority(memberRole.getRole().getValue()))
				.collect(Collectors.toList());

			return new User(member.getEmail(), member.getPassword(), authorities);
		}

		return null;
	}
}
