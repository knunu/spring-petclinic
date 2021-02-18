package org.springframework.samples.petclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.dto.SpecialtyDto;
import org.springframework.samples.petclinic.dto.VetDto;
import org.springframework.samples.petclinic.model.vet.Specialty;
import org.springframework.samples.petclinic.model.vet.Vet;
import org.springframework.samples.petclinic.repository.SpecialtyRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VetService {
	private final VetRepository vetRepository;
	private final SpecialtyRepository specialtyRepository;

	public Vet createVet(VetDto vetDto) {
		Vet vet = new Vet();
		vet.setFirstName(vetDto.getFirstName());
		vet.setLastName(vetDto.getLastName());

		Set<Specialty> specialties = new HashSet<>(specialtyRepository.findByName(vetDto.getSpecialtyNames()));
		vet.setSpecialties(specialties);

		return vetRepository.save(vet);
	}

	public List<Vet> getAllVets() {
		return vetRepository.findAll();
	}

	public Vet getVetById(Integer id) {
		return vetRepository.findById(id).orElse(null);
	}

	public List<Vet> getVetsByFirstName(String firstName) {
		return vetRepository.findByFirstName(firstName);
	}

	public List<Vet> getVetsByLastName(String lastName) {
		return vetRepository.findByLastName(lastName);
	}

	public List<Vet> getVetsBySpecialtyName(String specialtyName) {
		return vetRepository.findBySpecialtyName(specialtyName);
	}

	public Vet updateVet(Integer id, VetDto newVetDto) {
		Vet vet = vetRepository.findById(id).orElse(null);

		if (vet != null) {
			vet.setFirstName(newVetDto.getFirstName());
			vet.setLastName(newVetDto.getLastName());

			Set<Specialty> specialties = new HashSet<>(specialtyRepository.findByName(newVetDto.getSpecialtyNames()));
			vet.setSpecialties(specialties);

			vetRepository.save(vet);
		}

		return vet;
	}

	public void deleteVet(Vet vet) {
		vetRepository.delete(vet);
	}

	public void deleteVet(Integer id) {
		vetRepository.deleteById(id);
	}

}
