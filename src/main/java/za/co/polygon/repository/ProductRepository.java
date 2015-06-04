
package za.co.polygon.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.polygon.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {    
        
    @Query("select prod from product prod where prod.product_id=?1")
    public List<Product> findByProductId(Long productId);
    
}
