
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.polygon.domain.Broker;

@Repository
public interface BrokerRepository extends JpaRepository<Broker, Long> {
    
}
