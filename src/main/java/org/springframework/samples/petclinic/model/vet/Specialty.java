package org.springframework.samples.petclinic.model.vet;

import org.springframework.samples.petclinic.model.common.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "specialties")
public class Specialty extends NamedEntity {
}
