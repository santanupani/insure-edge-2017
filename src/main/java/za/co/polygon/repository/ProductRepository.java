
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.polygon.domain.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
    
    
    
}
