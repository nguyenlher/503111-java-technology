package edu.tdtu.Lab10.service;

import edu.tdtu.Lab10.dto.request.ProductRequest;
import edu.tdtu.Lab10.dto.response.ProductResponse;
import edu.tdtu.Lab10.execption.AppException;
import edu.tdtu.Lab10.execption.ErrorCode;
import edu.tdtu.Lab10.mapper.ProductMapper;
import edu.tdtu.Lab10.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor @Slf4j
@Service
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    public ProductResponse create(ProductRequest request) {
        var product = productMapper.createProduct(request);
        product = productRepository.save(product);
        return productMapper.toResponse(product);
    }

    public List<ProductResponse> getAll() {
        var products = productRepository.findAll();
        return products.stream().map(productMapper::toResponse).toList();
    }

    public ProductResponse update(ProductRequest request, String id) {
        if(!productRepository.existsById(id)) throw  new AppException(ErrorCode.ENTITY_NOT_EXISTS);
        var product = productRepository.findById(id) .orElseThrow();
        productMapper.update(product, request);
        return productMapper.toResponse(product);
    }

    public ProductResponse getById(String id) {
        if(!productRepository.existsById(id)) throw  new AppException(ErrorCode.ENTITY_NOT_EXISTS);
        var product = productRepository.getReferenceById(id);
        return productMapper.toResponse(product);
    }

    public void delete(String id) {
        if(!productRepository.existsById(id)) throw  new AppException(ErrorCode.ENTITY_NOT_EXISTS);
        productRepository.deleteById(id);
    }
}
