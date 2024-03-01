package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.WarehouseProduct;
import net.javaguides.springboot.service.WarehouseProductService;
import net.javaguides.springboot.service.WarehouseProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/warehouse-product")
public class WarehouseProductController {

    private WarehouseProductService WarehouseProductService;


    @GetMapping()
    public ResponseEntity<Page<WarehouseProduct>> getAllWarehouseProduct(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Integer warehouseId,
            @RequestParam(required = false) Integer productId
    ) {
        Page<WarehouseProduct> users = WarehouseProductService.getAllWarehouseProduct(sortBy, sortDir, page, size, warehouseId, productId);
        return ResponseEntity.ok(users);
    }

//    @GetMapping("{id}")
//    public ResponseEntity<User> getWarehouseProductById(@PathVariable("id") int WarehouseProductId) {
//        return new ResponseEntity<User>(WarehouseProductService.getWarehouseProductById(WarehouseProductId), HttpStatus.OK);
//    }
//    @PostMapping()
//    public ResponseEntity<User> saveEmployee(@RequestBody User User) {
//        return new ResponseEntity<User>(WarehouseProductService.saveWarehouseProduct(User), HttpStatus.CREATED);
//    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<User> updateWarehouseProduct(@PathVariable("id") int id
//            , @RequestBody User User) {
//        return new ResponseEntity<User>(WarehouseProductService.updateWarehouseProduct(User, id), HttpStatus.OK);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deleteWarehouseProduct(@PathVariable("id") int id) {
//        WarehouseProductService.deleteWarehouseProduct(id);
//        return new ResponseEntity<String>("WarehouseProduct deleted successfully!.", HttpStatus.OK);
//    }
}
