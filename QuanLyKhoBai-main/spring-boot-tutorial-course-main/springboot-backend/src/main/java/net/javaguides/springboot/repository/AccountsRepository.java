package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.javaguides.springboot.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
}
