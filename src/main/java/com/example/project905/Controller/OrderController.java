package com.example.project905.Controller;

import com.example.project905.Controller.Vm.RequestOrderVm;
import com.example.project905.Controller.Vm.ResponseOrderVm;

import com.example.project905.Dto.UserOrdersHistoryDto;
import com.example.project905.Modle.UserOrdersResponse;
import com.example.project905.Service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Management", description = "APIs for managing orders")
@CrossOrigin(origins = "http://localhost:4200") // ✅ إضافة CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Create order", description = "Create a new order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully created order"),
            @ApiResponse(responseCode = "400", description = "Invalid order data"),
            @ApiResponse(responseCode = "404", description = "Products or user not found")
    })@PostMapping("/create-order")
    public ResponseEntity<ResponseOrderVm> createOrder(
            @Valid @RequestBody RequestOrderVm requestOrderVm) {

        ResponseOrderVm response = orderService.requestOrder(requestOrderVm);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Get my orders", description = "Get logged-in user orders history")
    @GetMapping("/my-orders")
    public ResponseEntity<UserOrdersResponse> getMyOrders() {
        return ResponseEntity.ok(orderService.getMyOrders());
    }

    @Operation(summary = "Get all orders", description = "Get paginated list of all orders")
    @GetMapping
    public ResponseEntity<Page<com.example.project905.Dto.OrderDto>> findAll(
            @Parameter(description = "Page number (starting from 1)")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Page size (1-100)")
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(orderService.findAll(page, size));
    }

    @Operation(summary = "Delete order", description = "Delete an order by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Get all users with their orders",
            description = "Get paginated list of all users with their order history")
    @GetMapping("/users-history")
    public ResponseEntity<Page<UserOrdersHistoryDto>> getAllUsersWithOrders(
            @Parameter(description = "Page number (starting from 1)")
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Page size (1-100)")
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(orderService.getAllUsersWithOrders(page, size));
    }
}