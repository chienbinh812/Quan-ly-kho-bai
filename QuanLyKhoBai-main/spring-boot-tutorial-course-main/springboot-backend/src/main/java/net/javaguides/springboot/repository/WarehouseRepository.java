package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    @Query("SELECT u FROM Warehouse u  where u.id = ?1 and u.name = ?2")
    Page<Warehouse> findWarehouseByPageWithFilters(Pageable pageable, Integer id, String name);
}
