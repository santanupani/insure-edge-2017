
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.polygon.domain.ClientMasterData;

public interface ClientMasterDataRepository extends JpaRepository<ClientMasterData, Long>{
    
}
