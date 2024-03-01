package net.javaguides.springboot.service;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.WarehouseProduct;
import org.springframework.data.domain.Page;

public interface WarehouseProductService {
//    WarehouseProduct saveWarehouseProduct(WarehouseProduct warehouse);
//    List<WarehouseProduct> getAllWarehouseProduct();
//    WarehouseProduct getWarehouseProductById(int id);
//    WarehouseProduct updateWarehouseProduct(WarehouseProduct warehouse, int id);
//    void deleteWarehouseProduct(int id);
    Page<WarehouseProduct> getAllWarehouseProduct(String sortBy, String sortDir, Integer page, Integer size
            , Integer warehouseId, Integer productId);
}
