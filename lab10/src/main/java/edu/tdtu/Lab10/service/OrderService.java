package edu.tdtu.Lab10.service;

import edu.tdtu.Lab10.dto.request.OrderRequest;
import edu.tdtu.Lab10.dto.response.OrderResponse;
import edu.tdtu.Lab10.dto.request.ProductRequest;
import edu.tdtu.Lab10.dto.response.ProductResponse;
import edu.tdtu.Lab10.entity.Orders;
import edu.tdtu.Lab10.entity.Product;
import edu.tdtu.Lab10.execption.AppException;
import edu.tdtu.Lab10.execption.ErrorCode;
import edu.tdtu.Lab10.mapper.OrderMapper;
import edu.tdtu.Lab10.mapper.ProductMapper;
import edu.tdtu.Lab10.repository.OrderRepository;
import edu.tdtu.Lab10.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor @Slf4j
@Service
public class OrderService {
    OrderRepository orderRepository;
    ProductRepository productRepository;
    OrderMapper orderMapper;

    public OrderResponse create(OrderRequest request) {
        var order = orderMapper.createOrders(request);
        return addAndUpdate(order, request);
    }

    public List<OrderResponse> getAll() {
        var orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toOrderResponse).toList();
    }

    public OrderResponse update(OrderRequest request, Long id) {
        if(!orderRepository.existsById(id)) throw new AppException(ErrorCode.ENTITY_NOT_EXISTS);
        var order = orderRepository.findById(id) .orElseThrow();
        return addAndUpdate(order, request);
    }

    public OrderResponse getById(Long id) {
        if(!orderRepository.existsById(id)) throw new AppException(ErrorCode.ENTITY_NOT_EXISTS);
        var order = orderRepository.getReferenceById(id);
        return orderMapper.toOrderResponse(order);
    }

    public void delete(Long id) {
        if(!orderRepository.existsById(id)) throw new AppException(ErrorCode.ENTITY_NOT_EXISTS);
        orderRepository.deleteById(id);
    }

    private OrderResponse addAndUpdate(Orders order, OrderRequest request) {
        var products = productRepository.findAllById(request.getProducts());
        var totalPrice = products.stream()
                .mapToDouble(Product::getPrice)
                .reduce(0, Double::sum);
        order.setProducts(new HashSet<>(products));
        order.setTotalPrice(totalPrice);
        order = orderRepository.save(order);
        return orderMapper.toOrderResponse(order);
    }
}
