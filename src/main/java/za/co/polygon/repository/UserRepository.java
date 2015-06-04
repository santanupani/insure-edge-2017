package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}


