package za.co.polygon.service.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import za.co.polygon.mapper.UserMapper;
import za.co.polygon.repository.UserRepository;
import za.co.polygon.service.command.UserQuery;

@RestController
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserQuery> findAllUsers() {
		List<za.co.polygon.domain.User> users = userRepository.findAll();
		return UserMapper.toQueryModel(users);		
	}

}
