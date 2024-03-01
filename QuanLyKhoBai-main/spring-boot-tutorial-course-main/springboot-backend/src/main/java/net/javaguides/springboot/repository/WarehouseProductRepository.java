package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.UserWarehouse;
import net.javaguides.springboot.model.WarehouseProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseProductRepository extends JpaRepository<WarehouseProduct, Integer> {
//    @Query("SELECT wp FROM WarehouseProduct wp where (:id is null or wp.id = :id) "
//            + "and (:warehouseId is null or wp.warehouseId = :warehouseId) "
//            + "and (:productId is null or wp.productId = :productId) "
//            )
//    Page<UserWarehouse> findWarehouseProductByPageWithFilters(Pageable pageable, Integer userId, Integer warehouseId, Integer productId );
}
