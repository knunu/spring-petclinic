package org.springframework.samples.petclinic.controller.vet;

import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.model.vet.Vet;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VetViewController {
	private final VetService vetService;

	@GetMapping("/vets.html")
	public ModelAndView showVets() {
		ModelAndView mav = new ModelAndView("/vets/vetList");

		List<Vet> vets = vetService.getAllVets();
		mav.addObject("vets", vets);

		return mav;
	}

	@PostMapping("/vets/delete")
	public String deleteAndShowVets(@RequestParam("id") String vetId) {
		vetService.deleteVet(Integer.parseInt(vetId));

		return "redirect:/vets.html";
	}
}
