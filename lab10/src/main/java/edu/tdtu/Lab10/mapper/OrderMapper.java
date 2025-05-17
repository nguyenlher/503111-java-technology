package edu.tdtu.Lab10.mapper;

import edu.tdtu.Lab10.dto.request.OrderRequest;
import edu.tdtu.Lab10.dto.response.OrderResponse;
import edu.tdtu.Lab10.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "products", ignore = true)
    Orders createOrders(OrderRequest request);
    OrderResponse toOrderResponse(Orders order);
    @Mapping(target = "products", ignore = true)
    void update(@MappingTarget Orders order, OrderRequest request);
}
