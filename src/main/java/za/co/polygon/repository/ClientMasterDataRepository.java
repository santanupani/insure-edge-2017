
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.polygon.domain.ClientDetail;

public interface ClientMasterDataRepository extends JpaRepository<ClientDetail, Long>{
    
}
