
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.polygon.domain.QuotationRequest;

@Repository
public interface QuotationRequestRepository extends JpaRepository<QuotationRequest, Long> {
    
	public QuotationRequest findByReference(String reference);
}
