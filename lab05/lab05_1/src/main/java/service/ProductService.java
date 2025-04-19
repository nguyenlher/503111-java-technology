package service;

import models.Product;
import repository.ProductRepository;

import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }

    public boolean addProduct(String name, double price) {
        if (name == null || name.trim().isEmpty() || price <= 0) {
            return false;
        }

        Product product = new Product(name, price);
        return productRepository.save(product);
    }

    public boolean updateProduct(Long id, String name, double price) {
        if (name == null || name.trim().isEmpty() || price <= 0) {
            return false;
        }

        Product product = productRepository.findById(id);
        if (product == null) {
            return false;
        }

        product.setName(name);
        product.setPrice(price);
        return productRepository.update(product);
    }

    public boolean deleteProduct(Long id) {
        return productRepository.delete(id);
    }
}