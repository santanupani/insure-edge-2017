package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.polygon.domain.History;


public interface HistoryRepository extends JpaRepository<History, Long>{
    
}
