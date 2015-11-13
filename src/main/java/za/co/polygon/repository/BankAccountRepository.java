
package za.co.polygon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.polygon.domain.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    
}
