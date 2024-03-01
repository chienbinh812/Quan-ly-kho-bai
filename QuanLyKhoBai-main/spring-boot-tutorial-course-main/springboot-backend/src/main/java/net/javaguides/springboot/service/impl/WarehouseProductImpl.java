package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.WarehouseProduct;
import net.javaguides.springboot.repository.WarehouseProductRepository;
import net.javaguides.springboot.service.WarehouseProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class WarehouseProductImpl implements WarehouseProductService {
    private WarehouseProductRepository warehouseProductRepository;

    public WarehouseProductImpl(WarehouseProductRepository warehouseProductRepository) {
        this.warehouseProductRepository = warehouseProductRepository;
    }

//    @Override
//    public WarehouseProduct saveWarehouseProdut(WarehouseProduct WarehouseProduct) {
//        return WarehouseProductRepository.save(WarehouseProduct);
//    }
//
//    @Override
//    public List<WarehouseProduct> getAllWarehouseProduct() {
//        return WarehouseProductRepository.findAll();
//    }
//
//    @Override
//    public Page<WarehouseProduct> getAllWarehouseProdutsByPageWithFilters(String sortBy, String sortDir, Integer page, Integer size, Integer warehouseId, Integer productId) {
//        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//
//        // create Pageable instance
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        Page<WarehouseProduct> WarehouseProducts = WarehouseProductRepository.findAll(pageable);
////        return WarehouseProdutRepository.findWarehouseProdutsByPageWithFilters(pageable,id,name);
//        return WarehouseProducts;
//    }
//
//    @Override
//    public WarehouseProduct getWarehouseProdutById(int id) {
//        return WarehouseProductRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("WarehouseProduct", "Id", id));
//    }
//
//
//    @Override
//    public WarehouseProduct updateWarehouseProdut(WarehouseProduct WarehouseProdut, int id) {
//        WarehouseProduct existingWarehouseProduct = WarehouseProductRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("WarehouseProduct", "Id", id));
//        existingWarehouseProduct.setId(WarehouseProduct.getId());
//        existingWarehouseProduct.set(WarehouseProduct.getName());
//        existingWarehouseProduct.setRole(WarehouseProduct.getRole());
//        WarehouseProductRepository.save(existingWarehouseProdcut);
//        return existingWarehouseProduct;
//    }
//
//    @Override
//    public void deleteWarehouseProduct(int id) {
//        WarehouseProductRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("WarehouseProduct", "Id", id));
//        WarehouseProductRepository.deleteById(id);
//    }

    @Override
    public Page<WarehouseProduct> getAllWarehouseProduct(String sortBy, String sortDir, Integer page, Integer size, Integer warehouseId, Integer productId) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<WarehouseProduct> warehouseProducts = warehouseProductRepository.findAll(pageable);
//        return userRepository.findUsersByPageWithFilters(pageable,id,name);
        return warehouseProducts;
    }
}
