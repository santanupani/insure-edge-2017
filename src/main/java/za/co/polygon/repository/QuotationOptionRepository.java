
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.polygon.domain.QuotationOption;

@Repository
public interface QuotationOptionRepository extends JpaRepository<QuotationOption, Long> { 

}
