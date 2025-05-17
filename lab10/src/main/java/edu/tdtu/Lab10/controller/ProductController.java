package edu.tdtu.Lab10.controller;

import edu.tdtu.Lab10.dto.request.ProductRequest;
import edu.tdtu.Lab10.dto.response.ApiResponse;
import edu.tdtu.Lab10.dto.response.ProductResponse;
import edu.tdtu.Lab10.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @PostMapping
    ApiResponse<ProductResponse> create(@RequestBody ProductRequest request) {
        log.info("Check Add Product");
        return ApiResponse.<ProductResponse>builder()
                .result(productService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<ProductResponse>> getAll() {
        log.info("Check GetAll User");
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAll())
                .build();
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    ApiResponse<ProductResponse> update(@RequestBody ProductRequest request, @PathVariable String id) {
        log.info("Check Update Product");
        return ApiResponse.<ProductResponse>builder()
                .result(productService.update(request, id))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<ProductResponse> getById(@PathVariable String id) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getById(id))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id) {
        productService.delete(id);
        return ApiResponse.<Void>builder().build();
    }
}

