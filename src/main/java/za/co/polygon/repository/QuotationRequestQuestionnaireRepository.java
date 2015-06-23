
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.polygon.domain.QuotationRequestQuestionnaires;


@Repository
public interface QuotationRequestQuestionnaireRepository extends JpaRepository<QuotationRequestQuestionnaires, Long> {
       
}
