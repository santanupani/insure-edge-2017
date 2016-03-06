package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.polygon.domain.Carrier;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    
    public  Carrier findByDescription(String description);

}
