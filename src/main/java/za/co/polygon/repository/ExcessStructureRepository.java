package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.polygon.domain.ExcessStructure;

@Repository
public interface ExcessStructureRepository extends JpaRepository<ExcessStructure, Long> {

}
