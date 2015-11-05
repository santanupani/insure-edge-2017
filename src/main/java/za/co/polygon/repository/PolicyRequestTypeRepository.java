package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.PolicyRequestType;

public interface PolicyRequestTypeRepository extends JpaRepository<PolicyRequestType, Long> {
	public PolicyRequestType findByReference(String reference);
}
