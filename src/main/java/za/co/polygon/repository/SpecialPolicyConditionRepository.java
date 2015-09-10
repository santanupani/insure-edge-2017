package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.polygon.domain.SpecialPolicyCondition;

@Repository
public interface SpecialPolicyConditionRepository extends JpaRepository<SpecialPolicyCondition, Long> {

}
