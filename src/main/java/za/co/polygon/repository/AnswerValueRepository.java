package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.polygon.domain.AnswerValue;

@Repository
public interface AnswerValueRepository extends JpaRepository<AnswerValue, Long> {

}
