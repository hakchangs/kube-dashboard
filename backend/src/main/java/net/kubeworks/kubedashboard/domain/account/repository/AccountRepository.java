package net.kubeworks.kubedashboard.domain.account.repository;

import net.kubeworks.kubedashboard.domain.account.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
