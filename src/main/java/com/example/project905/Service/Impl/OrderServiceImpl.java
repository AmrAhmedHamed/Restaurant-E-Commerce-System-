package com.example.project905.Service.Impl;

import com.example.project905.Controller.Vm.RequestOrderVm;
import com.example.project905.Controller.Vm.ResponseOrderVm;
import com.example.project905.Dto.*;
import com.example.project905.Mapper.OrderMapper;
import com.example.project905.Mapper.ProductMapper;
import com.example.project905.Modle.Order;
import com.example.project905.Modle.Product;
import com.example.project905.Modle.User;
import com.example.project905.Modle.UserOrdersResponse;
import com.example.project905.Repo.OrderRepo;
import com.example.project905.Repo.ProductRepo;
import com.example.project905.Repo.UserRepo;
import com.example.project905.Service.MassegeHandler.bandlMassegeService;
import com.example.project905.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final OrderMapper orderMapper;
    private final bandlMassegeService messageService;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, ProductRepo productRepo,
                            UserRepo userRepo, OrderMapper orderMapper,
                            bandlMassegeService messageService) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.orderMapper = orderMapper;
        this.messageService = messageService;
    }
    @Override
    public ResponseOrderVm requestOrder(RequestOrderVm requestOrderVm) {

        List<Product> products =
                productRepo.findAllById(requestOrderVm.getProductsIds());

        if (products.size() != requestOrderVm.getProductsIds().size()) {
            throw new RuntimeException(
                    messageService.getMassegeEn("products.not.found"));
        }

        User user = getCurrentUser();

        Order order = new Order();
        order.setProducts(products);
        order.setTotalPrice(requestOrderVm.getTotalPrice());
        order.setTotalNumber(requestOrderVm.getTotalNumber());
        order.setUser(user);

        Order saved = orderRepo.save(order);

        saved.setCode("ORD-" + saved.getId());
        orderRepo.save(saved);

        return new ResponseOrderVm(
                saved.getCode(),
                saved.getTotalPrice(),
                saved.getTotalNumber()
        );
    }


    @Override
    public UserOrdersResponse getMyOrders() {

        User user = getCurrentUser();

        List<Order> orders = orderRepo.findByUserId(user.getId());

        List<OrderDto> orderDtos = orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());

        double totalPrice = orderDtos.stream()
                .mapToDouble(OrderDto::getTotalPrice)
                .sum();

        return new UserOrdersResponse(
                orderDtos,
                orderDtos.size(),
                totalPrice
        );
    }


    @Override
    public Page<OrderDto> findAll(int page, int size) {
        validatePaginationParams(page, size);
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Order> orders = orderRepo.findAll(pageable);

        return orders.map(orderMapper::toDto);
    }


    @Override
    public void delete(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        messageService.getMassegeEn("order.not.found")
                ));
        orderRepo.delete(order);
    }
    @Override
    public Page<UserOrdersHistoryDto> getAllUsersWithOrders(int page, int size) {
        validatePaginationParams(page, size);
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<User> usersPage = userRepo.findAll(pageable);

        List<User> usersWithOrders = usersPage.getContent().stream()
                .filter(user -> {
                    List<Order> orders = orderRepo.findByUserId(user.getId());
                    return !orders.isEmpty();
                })
                .collect(Collectors.toList());

        Page<User> filteredPage = new PageImpl<>(
                usersWithOrders,
                pageable,
                usersWithOrders.size()
        );

        return filteredPage.map(user -> {
            List<Order> orders = orderRepo.findByUserId(user.getId());
            List<OrderDto> orderDtos = orders.stream()
                    .map(orderMapper::toDto)
                    .collect(Collectors.toList());

            double totalSpent = orders.stream()
                    .mapToDouble(Order::getTotalPrice)
                    .sum();

            return new UserOrdersHistoryDto(
                    user.getId(),
                    user.getUsername(),
                    orderDtos,
                    orders.size(),
                    totalSpent
            );
        });
    }



    private void validatePaginationParams(int page, int size) {
        if (page < 1) {
            throw new IllegalArgumentException(messageService.getMassegeEn("invalid.page.number"));
        }
        if (size < 1 || size > 100) {
            throw new IllegalArgumentException(messageService.getMassegeEn("invalid.page.size"));
        }
    }
    private User getCurrentUser() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof org.springframework.security.core.userdetails.User userDetails) {
            return userRepo.findByUsername(userDetails.getUsername())
                    .orElseThrow(() ->
                            new RuntimeException("user.not.found"));
        }

        throw new RuntimeException("unauthorized");
    }


}