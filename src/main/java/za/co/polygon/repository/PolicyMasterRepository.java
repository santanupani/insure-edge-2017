package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.ClientPolicy;

public interface PolicyMasterRepository extends JpaRepository<ClientPolicy,Long>{

}
