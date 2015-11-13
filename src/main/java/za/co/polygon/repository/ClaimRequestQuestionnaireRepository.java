package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.ClaimAnswer;


public interface ClaimRequestQuestionnaireRepository extends JpaRepository<ClaimAnswer, Long>{
    
}
