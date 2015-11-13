
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.ClaimType;

public interface ClaimTypeRepository extends JpaRepository<ClaimType, Long> {
   
}
