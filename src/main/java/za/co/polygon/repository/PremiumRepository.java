
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.polygon.domain.Premium;

@Repository
public interface PremiumRepository extends JpaRepository<Premium, Long> {
    
}
