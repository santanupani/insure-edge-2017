
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.polygon.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> { 
    
 }
