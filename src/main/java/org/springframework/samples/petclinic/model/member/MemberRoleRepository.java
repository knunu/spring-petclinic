package org.springframework.samples.petclinic.model.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Integer> {
	Optional<MemberRole> findByRole(MemberRole.Role role);
}
