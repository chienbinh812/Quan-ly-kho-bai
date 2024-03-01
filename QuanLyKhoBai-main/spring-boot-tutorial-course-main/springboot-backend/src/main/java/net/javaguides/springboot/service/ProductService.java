package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProduct();
    Product getProductById(int id);
    Product updateProduct(Product product, int id);
    void deleteProduct(int id);

    Page<Product> getAllProductByPageWithFilters(String sortBy, String sortDir, Integer page, Integer size
            , Integer id, String name);
}
