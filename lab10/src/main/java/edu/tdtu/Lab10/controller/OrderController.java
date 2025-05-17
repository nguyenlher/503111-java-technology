package edu.tdtu.Lab10.controller;

import edu.tdtu.Lab10.dto.request.OrderRequest;
import edu.tdtu.Lab10.dto.response.OrderResponse;
import edu.tdtu.Lab10.dto.request.ProductRequest;
import edu.tdtu.Lab10.dto.response.ApiResponse;
import edu.tdtu.Lab10.dto.response.ProductResponse;
import edu.tdtu.Lab10.mapper.OrderMapper;
import edu.tdtu.Lab10.service.OrderService;
import edu.tdtu.Lab10.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;

    @PostMapping
    ApiResponse<OrderResponse> create(@RequestBody OrderRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<OrderResponse>> getAll() {
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getAll())
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<OrderResponse> update(@RequestBody OrderRequest request, @PathVariable Long id) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.update(request, id))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<OrderResponse> getById(@PathVariable Long id) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.getById(id))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ApiResponse.<Void>builder().build();
    }
}

