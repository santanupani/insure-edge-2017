
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.polygon.domain.QuotationRequestQuestionnaires;



public interface QuotationRequestQuestionnaire extends JpaRepository<QuotationRequestQuestionnaires, Long> {
    
    
}
