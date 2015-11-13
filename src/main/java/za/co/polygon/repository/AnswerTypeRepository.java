package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.polygon.domain.AnswerType;

@Repository
public interface AnswerTypeRepository extends JpaRepository<AnswerType, Long> {

}
