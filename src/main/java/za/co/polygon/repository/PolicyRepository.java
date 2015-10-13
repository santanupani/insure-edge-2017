package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.Policy;

public interface PolicyRepository extends JpaRepository<Policy,Long>{
	public Policy findByReference(String Reference);

}
