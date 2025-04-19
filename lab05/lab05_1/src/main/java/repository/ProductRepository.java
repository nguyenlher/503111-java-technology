package repository;

import dao.ProductDAO;
import models.Product;

import java.util.List;

public class ProductRepository {
    private final ProductDAO productDAO;

    public ProductRepository() {
        productDAO = new ProductDAO();
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public Product findById(Long id) {
        return productDAO.findById(id);
    }

    public boolean save(Product product) {
        return productDAO.save(product);
    }

    public boolean update(Product product) {
        return productDAO.update(product);
    }

    public boolean delete(Long id) {
        return productDAO.delete(id);
    }
}