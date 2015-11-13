
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.polygon.domain.LocationOption;

@Repository
public interface LocationOptionRepository extends JpaRepository<LocationOption, Long> {
    
}
