package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT u FROM Product u  where u.id = ?1 and u.name = ?2")
    Page<Product> findProductByPageWithFilters(Pageable pageable, Integer id, String name);
}
