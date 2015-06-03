package za.co.polygon.mapper;

import java.util.ArrayList;
import java.util.List;

import za.co.polygon.domain.User;
import za.co.polygon.service.command.UserQuery;


public class UserMapper {
	
	public static UserQuery toQueryModel(User from){
		UserQuery user = new UserQuery();
		user.setId(from.getId());
		user.setUserName(from.getUserName());
		user.setRole(from.getRole());
		return user;
    }
	
	public static List<UserQuery> toQueryModel(List<User> fromList){
		List<UserQuery> userList = new ArrayList<UserQuery>();
		for(User user: fromList){
			userList.add(toQueryModel(user));
		}
		return userList;
    }

}
