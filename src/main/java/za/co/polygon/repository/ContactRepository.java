package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.Contact;


public interface ContactRepository extends JpaRepository<Contact, Long>{
    
}
