
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.polygon.domain.Answer;


@Repository
public interface QuotationRequestQuestionnaireRepository extends JpaRepository<Answer, Long> {
       
}
