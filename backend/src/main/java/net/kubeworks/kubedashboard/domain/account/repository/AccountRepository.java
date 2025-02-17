package net.kubeworks.kubedashboard.domain.account.repository;

import net.kubeworks.kubedashboard.domain.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByUsername(String username);

    Optional<Account> findByUsername(String username);

}
