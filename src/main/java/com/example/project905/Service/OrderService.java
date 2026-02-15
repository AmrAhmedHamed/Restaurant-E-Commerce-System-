package com.example.project905.Service;

import com.example.project905.Controller.Vm.RequestOrderVm;
import com.example.project905.Controller.Vm.ResponseOrderVm;
import com.example.project905.Dto.OrderDto;
import com.example.project905.Dto.UserOrdersHistoryDto;
import com.example.project905.Modle.UserOrdersResponse;
import org.springframework.data.domain.Page;

public interface OrderService {
    ResponseOrderVm requestOrder(RequestOrderVm requestOrderVm);
    UserOrdersResponse getMyOrders();
    Page<OrderDto> findAll(int page, int size);
    void delete(Long id);
    Page<UserOrdersHistoryDto> getAllUsersWithOrders(int page, int size);


}