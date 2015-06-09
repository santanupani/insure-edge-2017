package za.co.polygon.mapper;

import java.util.ArrayList;
import java.util.List;
import za.co.polygon.domain.Product;
import za.co.polygon.domain.User;
import za.co.polygon.model.ProductQueryModel;
import za.co.polygon.model.UserQueryModel;

public class Mapper {

    public static UserQueryModel toUserQueryModel(User from) {
        UserQueryModel user = new UserQueryModel();
        user.setId(from.getId());
        user.setUserName(from.getUserName());
        user.setRole(from.getRole());
        return user;
    }

    public static List<UserQueryModel> toUserQueryModel(List<User> fromList) {
        List<UserQueryModel> userList = new ArrayList<UserQueryModel>();
        for (User user : fromList) {
            userList.add(toUserQueryModel(user));
        }
        return userList;
    }

    public static ProductQueryModel toProductQueryModel(Product from) {
        ProductQueryModel productQueryModel = new ProductQueryModel();
        productQueryModel.setId(from.getId());
        productQueryModel.setName(from.getName());
        productQueryModel.setDescription(from.getDescription());
        productQueryModel.setImage(from.getImage());
        return productQueryModel;
    }

    public static List<ProductQueryModel> toProductQueryModel(List<Product> fromList) {
        List<ProductQueryModel> productQueryModels = new ArrayList<ProductQueryModel>();
        for (Product product : fromList) {
            productQueryModels.add(toProductQueryModel(product));

        }
        return productQueryModels;
    }

}
