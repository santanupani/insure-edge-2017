package za.co.polygon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static za.co.polygon.mapper.Mapper.*;
import za.co.polygon.model.UserQueryModel;
import za.co.polygon.repository.UserRepository;

@RestController
public class Service {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserQueryModel> findAllUsers() {
		List<za.co.polygon.domain.User> users = userRepository.findAll();
		return toUserQueryModel(users);		
	}

}
