package edu.tdtu.Lab10.mapper;

import edu.tdtu.Lab10.dto.request.ProductRequest;
import edu.tdtu.Lab10.dto.response.ProductResponse;
import edu.tdtu.Lab10.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product createProduct(ProductRequest request);
    ProductResponse toResponse(Product product);
    void update(@MappingTarget Product product, ProductRequest request);
}
