
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.polygon.domain.MessageBody;

public interface MessageBodyRepository extends JpaRepository<MessageBody, Long>{
    
}
