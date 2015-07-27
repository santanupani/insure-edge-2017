
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.polygon.domain.PolicyRequest;

public interface PolicyRequestRepository  extends JpaRepository<PolicyRequest, Long> {
    
}
