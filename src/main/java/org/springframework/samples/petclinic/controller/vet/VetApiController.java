package org.springframework.samples.petclinic.controller.vet;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.dto.VetDto;
import org.springframework.samples.petclinic.model.vet.Vet;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vets")
@RequiredArgsConstructor
public class VetApiController {
	private final VetService vetService;

	@GetMapping
	public List<Vet> getAllVets() {
		return vetService.getAllVets();
	}

	@GetMapping("/{id}")
	public Vet getVetById(@PathVariable("id") int id) {
		return vetService.getVetById(id);
	}

	@GetMapping("/first-name/{firstName}")
	public List<Vet> getVetsByFirstName(@PathVariable("firstName") String firstName) {
		return vetService.getVetsByFirstName(firstName);
	}

	@GetMapping("/last-name/{lastName}")
	public List<Vet> getVetsByLastName(@PathVariable("lastName") String lastName) {
		return vetService.getVetsByLastName(lastName);
	}

	@GetMapping("/specialties/{specialtyName}")
	public List<Vet> getVetsBySpecialtyName(@PathVariable("specialtyName") String specialtyName) {
		return vetService.getVetsBySpecialtyName(specialtyName);
	}

	@PostMapping
	public ResponseEntity<Vet> createVet(@RequestBody VetDto vetDto) {
		return new ResponseEntity<>(vetService.createVet(vetDto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Vet> updateVet(@PathVariable("id") int id, @RequestBody VetDto vetDto) {
		return new ResponseEntity<>(vetService.updateVet(id, vetDto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVet(@PathVariable("id") int id) {
		vetService.deleteVet(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
