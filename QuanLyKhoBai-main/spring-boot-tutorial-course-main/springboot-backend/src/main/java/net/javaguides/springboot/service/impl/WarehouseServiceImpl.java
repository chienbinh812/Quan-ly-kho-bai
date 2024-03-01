package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.Warehouse;
import net.javaguides.springboot.repository.WarehouseRepository;
import net.javaguides.springboot.service.WarehouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        super();
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Page<Warehouse> getAllWarehouseByPageWithFilters(String sortBy, String sortDir, Integer page, Integer size, Integer id, String username) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Warehouse> warehouses = warehouseRepository.findAll(pageable);
//        return userRepository.findUsersByPageWithFilters(pageable,id,name);
        return warehouses;
    }

    @Override
    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();    }

    @Override
    public Warehouse getWarehouseById(int id) {
        return warehouseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Warehouse", "Id", id));
    }

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse, int id) {
        // we need to check whether Warehouse with given id is exist in DB or not
        Warehouse existingWarehouse = warehouseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Warehouse", "Id", id));

        existingWarehouse.setName(warehouse.getName());
        existingWarehouse.setAddress(warehouse.getAddress());
        // save existing Warehouse to DB
        warehouseRepository.save(existingWarehouse);
        return existingWarehouse;
    }

    @Override
    public void deleteWarehouse(int id) {
        warehouseRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Warehouse", "Id", id));
        warehouseRepository.deleteById(id);
    }
}
