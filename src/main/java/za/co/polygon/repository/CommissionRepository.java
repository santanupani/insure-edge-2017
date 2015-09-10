package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.Commission;

public interface CommissionRepository extends JpaRepository<Commission,Long>{

}
