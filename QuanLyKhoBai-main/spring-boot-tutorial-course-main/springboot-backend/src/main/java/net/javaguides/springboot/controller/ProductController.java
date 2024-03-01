package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping
//    public List<Product> getAllProduct() {
//        return productService.getAllProduct();
//    }

    @GetMapping()
    public ResponseEntity<Page<Product>> getAllProductsByPageWithFilters(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name
    ) {
        Page<Product> products = productService.getAllProductByPageWithFilters(sortBy, sortDir, page, size, id, name);
        return ResponseEntity.ok(products);
    }

    // build get Product by id REST API
    // http://localhost:8080/api/Product/1
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int ProductId) {
        return new ResponseEntity<Product>(productService.getProductById(ProductId), HttpStatus.OK);
    }

    // build create employee REST API
    @PostMapping()
    public ResponseEntity<Product> saveEmployee(@RequestBody Product Product) {
        return new ResponseEntity<Product>(productService.saveProduct(Product), HttpStatus.CREATED);
    }

    // build update employee REST API
    // http://localhost:8080/api/Product/1
    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id
            , @RequestBody Product Product) {
        return new ResponseEntity<Product>(productService.updateProduct(Product, id), HttpStatus.OK);
    }

    // build delete employee REST API
    // http://localhost:8080/api/product/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
        // delete employee from DB
        productService.deleteProduct(id);
        return new ResponseEntity<String>("Product deleted successfully!.", HttpStatus.OK);
    }
}
