
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.ClaimRequest;


public interface ClaimRequestRepository extends JpaRepository<ClaimRequest, Long>{
    // public QuotationRequest findByReference(String reference);
    
    public  ClaimRequest findByClaimNumber(String claimNumber);
}
