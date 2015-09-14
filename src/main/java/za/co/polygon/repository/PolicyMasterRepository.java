package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.PolicyDetail;

public interface PolicyMasterRepository extends JpaRepository<PolicyDetail,Long>{

}
