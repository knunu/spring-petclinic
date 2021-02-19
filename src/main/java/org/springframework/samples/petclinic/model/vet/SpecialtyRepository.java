package org.springframework.samples.petclinic.model.vet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.vet.Specialty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
	@Query("select specialty from Specialty specialty where specialty.name in (:specialtyNames)")
	List<Specialty> findByName(@Param("specialtyNames") List<String> name);
}
