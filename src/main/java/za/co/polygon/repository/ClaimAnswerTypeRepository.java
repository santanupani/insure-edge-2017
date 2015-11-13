package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.ClaimAnswerType;

public interface ClaimAnswerTypeRepository extends JpaRepository<ClaimAnswerType, Long> {
    
}
