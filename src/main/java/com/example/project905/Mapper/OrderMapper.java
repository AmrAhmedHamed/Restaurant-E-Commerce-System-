package com.example.project905.Mapper;

import com.example.project905.Controller.Vm.ResponseOrderVm;
import com.example.project905.Dto.OrderDto;
import com.example.project905.Modle.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderMapper {

    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "userId", source = "user.id")
    OrderDto toDto(Order order);

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "user", ignore = true)
    Order toEntity(OrderDto orderDto);

    ResponseOrderVm toResponseVm(Order order);

    List<OrderDto> toOrderDtoList(List<Order> orders);
}