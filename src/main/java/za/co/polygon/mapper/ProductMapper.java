package za.co.polygon.mapper;

import static com.sun.jndi.dns.DnsUrl.fromList;
import java.util.ArrayList;
import java.util.List;
import za.co.polygon.domain.Product;
import za.co.polygon.service.command.ProductQuery;

public class ProductMapper {

    public static ProductQuery toQueryModel(Product from) {
        ProductQuery product = new ProductQuery();
        product.setProduct_id(from.getProduct_id());
        product.setProductname(from.getProductname());
        product.setProductDescription(from.getProductDescription());
        //product.setImage(from.getImage());
        return product;
    }

    public static List<ProductQuery> toQueryModel(List<Product> fromList) {
        List<ProductQuery> productList = new ArrayList<ProductQuery>();
        for (Product product : fromList) {
            productList.add(toQueryModel(product));

        }
        return productList;
    }

    public static List<ProductQuery> findOne(Long product_id) {

        List<ProductQuery> productList = new ArrayList<ProductQuery>();
        
        ProductQuery productQuery = new ProductQuery();
        productQuery.setProduct_id(1L);
        productQuery.setProductname("Test");
        productQuery.setProductDescription("Test description");
        productList.add(productQuery);
        
        return productList;

    }

}
