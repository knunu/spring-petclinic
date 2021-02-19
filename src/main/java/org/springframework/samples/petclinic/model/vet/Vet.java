package org.springframework.samples.petclinic.model.vet;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.samples.petclinic.configuration.CacheConfiguration;
import org.springframework.samples.petclinic.model.common.Person;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vets")
@Getter
@Setter
@Cacheable
@org.hibernate.annotations.Cache(region = CacheConfiguration.DB_CACHE, usage = CacheConcurrencyStrategy.READ_ONLY)
public class Vet extends Person {
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
		inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private Set<Specialty> specialties;
}
