package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.ProductRepository;
import net.javaguides.springboot.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();    }

    @Override
    public Page<Product> getAllProductByPageWithFilters(String sortBy, String sortDir, Integer page, Integer size, Integer id, String name) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> products = productRepository.findAll(pageable);
//        return userRepository.findUsersByPageWithFilters(pageable,id,name);
        return products;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id));
    }

    @Override
    public Product updateProduct(Product product, int id) {
        // we need to check whether user with given id is exist in DB or not
        Product existingProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "Id", id));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        existingProduct.setDateOfManufacture(product.getDateOfManufacture());
        // save existing user to DB
        productRepository.save(existingProduct);
        return existingProduct;
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id));
        productRepository.deleteById(id);
    }
}
