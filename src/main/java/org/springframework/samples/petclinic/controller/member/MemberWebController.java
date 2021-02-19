package org.springframework.samples.petclinic.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.dto.LoginDto;
import org.springframework.samples.petclinic.dto.SignupDto;
import org.springframework.samples.petclinic.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberWebController {
	private final MemberService memberService;

	@GetMapping("/login")
	public String login(Map<String, Object> model) {
		LoginDto loginDto = new LoginDto();
		model.put("loginDto", loginDto);

		return "members/login";
	}

	@GetMapping("/signup")
	public String signup(Map<String, Object> model) {
		SignupDto signupDto = new SignupDto();
		model.put("signupDto", signupDto);

		return "members/signup";
	}

	@PostMapping("/signup")
	public String processSignup(SignupDto memberDto) {
		memberService.joinMember(memberDto);

		return "redirect:/login";
	}

	@PostMapping("/logout")
	public String processLogout() {
		return "redirect:/login";
	}
}
