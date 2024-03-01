package net.javaguides.springboot.repository;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Account;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.model.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
//    @Query("SELECT u FROM Account u  where u.id = ?1 and u.username = ?2")
    Optional<Account> findByUsername(String username);
    default Account getUserByName(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }
}
