
package za.co.polygon.service.query;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.polygon.mapper.ProductMapper;
import za.co.polygon.repository.ProductRepository;
import za.co.polygon.service.command.ProductQuery;


@RestController
public class ProductService {
    
    @Autowired
	private ProductRepository productRepository;
    
    @RequestMapping(value = "api/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductQuery> findAllProducts() {
		List<za.co.polygon.domain.Product> products = productRepository.findAll();
                return ProductMapper.toQueryModel(products);
	}
        
        
    
}
