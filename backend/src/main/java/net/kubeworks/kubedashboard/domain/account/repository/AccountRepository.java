package net.kubeworks.kubedashboard.domain.account.repository;

import net.kubeworks.kubedashboard.domain.account.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    boolean existsByUsername(String username);

    Optional<AccountEntity> findByUsername(String username);

}
