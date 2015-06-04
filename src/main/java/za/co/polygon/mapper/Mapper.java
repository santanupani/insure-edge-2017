package za.co.polygon.mapper;

import java.util.ArrayList;
import java.util.List;

import za.co.polygon.domain.User;
import za.co.polygon.model.UserQueryModel;


public class Mapper {
	
	public static UserQueryModel toUserQueryModel(User from){
		UserQueryModel user = new UserQueryModel();
		user.setId(from.getId());
		user.setUserName(from.getUserName());
		user.setRole(from.getRole());
		return user;
    }
	
	public static List<UserQueryModel> toUserQueryModel(List<User> fromList){
		List<UserQueryModel> userList = new ArrayList<UserQueryModel>();
		for(User user: fromList){
			userList.add(toUserQueryModel(user));
		}
		return userList;
    }

}
