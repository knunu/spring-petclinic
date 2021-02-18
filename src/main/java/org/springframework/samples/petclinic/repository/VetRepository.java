package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.vet.Vet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetRepository extends JpaRepository<Vet, Integer> {
	List<Vet> findByFirstName(String firstName);

	List<Vet> findByLastName(String lastName);

	@Query("select vet from Vet vet join vet.specialties specialties where specialties.name = :specialtyName")
	List<Vet> findBySpecialtyName(@Param("specialtyName") String specialtyName);

//	@Modifying
//	@Query("update Vet vet set vet.firstName = :#{#newVet.firstName}, vet.lastName = :#{#newVet.lastName}, vet.specialties = :#{#newVet.specialties} where vet.id = :id")
//	Vet update(@Param("id") Integer id, @Param("newVet") Vet newVet);
}
