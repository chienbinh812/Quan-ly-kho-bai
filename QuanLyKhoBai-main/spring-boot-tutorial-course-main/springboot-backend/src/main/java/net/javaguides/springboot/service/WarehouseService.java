package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.model.Warehouse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WarehouseService {
    Warehouse saveWarehouse(Warehouse warehouse);
    List<Warehouse> getAllWarehouse();
    Warehouse getWarehouseById(int id);
    Warehouse updateWarehouse(Warehouse warehouse, int id);
    void deleteWarehouse(int id);

    Page<Warehouse> getAllWarehouseByPageWithFilters(String sortBy, String sortDir, Integer page, Integer size
            , Integer id, String username);
}
