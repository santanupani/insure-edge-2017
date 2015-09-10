package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.polygon.domain.UnderwritterGeneral;

@Repository
public interface UnderwritterGeneralRepository extends JpaRepository<UnderwritterGeneral, Long> {

}
