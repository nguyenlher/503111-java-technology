package edu.tdtu.Lab10.repository;

import edu.tdtu.Lab10.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> { }
