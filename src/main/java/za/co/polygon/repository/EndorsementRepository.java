package za.co.polygon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.Endorsement;

public interface EndorsementRepository extends JpaRepository<Endorsement, Long> {
	public List<Endorsement> findByPolicyId(Long policyId);
}
