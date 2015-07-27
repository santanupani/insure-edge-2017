
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.polygon.domain.PolicyRequest;
import za.co.polygon.domain.Quotation;

public interface PolicyRequestRepository  extends JpaRepository<PolicyRequest, Long> {
     public PolicyRequest findByQuotation(Quotation quotation);
}
