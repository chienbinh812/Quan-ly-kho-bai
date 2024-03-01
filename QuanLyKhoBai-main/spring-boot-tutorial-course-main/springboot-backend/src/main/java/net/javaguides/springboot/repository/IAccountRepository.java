package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByUsername(String username);
    boolean existsByUsername(String username);

    @Query("SELECT a FROM Account a WHERE " +
            "a.username LIKE CONCAT('%',:query, '%')")
    List<Accounts> searchAccounts(String query);
}
