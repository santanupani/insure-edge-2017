package za.co.polygon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.ClaimQuestionnaire;
import za.co.polygon.domain.ClaimType;


public interface ClaimQuestionnaireRepository extends JpaRepository<ClaimQuestionnaire, Long> {
 
    public List<ClaimQuestionnaire> findByClaimType(ClaimType claimType);
}
