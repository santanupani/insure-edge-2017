package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.polygon.domain.ClaimRequest;
import za.co.polygon.domain.ReleaseForm;

@Repository
public interface ReleaseFormRepository extends JpaRepository<ReleaseForm, Long>{
    
    public ClaimRequest findByClaimRequest(ClaimRequest claimRequest);
    
}
