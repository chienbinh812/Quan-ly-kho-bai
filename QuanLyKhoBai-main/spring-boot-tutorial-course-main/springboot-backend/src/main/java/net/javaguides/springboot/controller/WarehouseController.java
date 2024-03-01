package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.Warehouse;
import net.javaguides.springboot.service.WarehouseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    private WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

//    @GetMapping
//    public List<Warehouse> getAllWarehouse() {
//
//        return warehouseService.getAllWarehouse();
//    }

    @GetMapping()
    public ResponseEntity<Page<Warehouse>> getAllWarehouseByPageWithFilters(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name
    ) {
        Page<Warehouse> warehouses = warehouseService.getAllWarehouseByPageWithFilters(sortBy, sortDir, page, size, id, name);
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable("id") int WarehouseId) {
        return new ResponseEntity<Warehouse>(warehouseService.getWarehouseById(WarehouseId), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Warehouse> saveEmployee(@RequestBody Warehouse Warehouse) {
        return new ResponseEntity<Warehouse>(warehouseService.saveWarehouse(Warehouse), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable("id") int id
            , @RequestBody Warehouse Warehouse) {
        return new ResponseEntity<Warehouse>(warehouseService.updateWarehouse(Warehouse, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable("id") int id) {
        warehouseService.deleteWarehouse(id);
        return new ResponseEntity<String>("Warehouse deleted successfully!.", HttpStatus.OK);
    }
}
