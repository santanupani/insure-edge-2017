
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.polygon.domain.Quotation;
import za.co.polygon.domain.QuotationRequest;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> { 
    
    public Quotation findByQuotationRequest(QuotationRequest quotationRequest);
}
