package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
