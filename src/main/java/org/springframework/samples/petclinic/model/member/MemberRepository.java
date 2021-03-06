package org.springframework.samples.petclinic.model.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
	Optional<Member> findByEmail(@Param("email") String email);
}
