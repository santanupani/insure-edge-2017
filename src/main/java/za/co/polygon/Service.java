package za.co.polygon;

import java.util.List;

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserQueryModel> findAllUsers() {
        List<za.co.polygon.domain.User> users = userRepository.findAll();
        return toUserQueryModel(users);
    }

    @RequestMapping(value = "api/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductQueryModel> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return toProductQueryModel(products);
    }

}
