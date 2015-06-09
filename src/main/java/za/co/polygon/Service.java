package za.co.polygon;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.polygon.domain.Product;
import static za.co.polygon.mapper.Mapper.*;
import za.co.polygon.model.ProductQueryModel;
import za.co.polygon.model.UserQueryModel;
import za.co.polygon.repository.ProductRepository;
import za.co.polygon.repository.UserRepository;

@RestController
public class Service {
    
    private static final Logger log = LoggerFactory.getLogger(Service.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "api/users/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserQueryModel> findAllUsers() {
        log.info("find user");
        List<za.co.polygon.domain.User> users = userRepository.findAll();
        List<UserQueryModel> result = toUserQueryModel(users);
        log.info("found user, size:{}", result.size());
        log.info("this service to get all users");
        return result;
    }

    @RequestMapping(value = "api/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductQueryModel> findAllProducts() {
    	log.info("find all products");
        List<Product> products = productRepository.findAll();
        List<ProductQueryModel> result = toProductQueryModel(products);
        log.info("found all products, size:{}", result.size());
        return result;
    }

}
