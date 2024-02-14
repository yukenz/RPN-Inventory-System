package com.awanrpn.invenmanager.controller;

import com.awanrpn.invenmanager.model.dto.ResponsePayloadBuilder;
import com.awanrpn.invenmanager.model.dto.order.CreateOrderResponse;
import com.awanrpn.invenmanager.model.dto.order.GetAllOrderResponse;
import com.awanrpn.invenmanager.model.dto.order.GetOrderByIdResponse;
import com.awanrpn.invenmanager.model.dto.order.GetOrderResponse;
import com.awanrpn.invenmanager.model.dto.orderItem.GetOrderItemResponse;
import com.awanrpn.invenmanager.model.dto.user.GetUserByIdResponse;
import com.awanrpn.invenmanager.service.OrderService;
import com.awanrpn.invenmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order Module")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @Operation(summary = "Create Order")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    createOrder(
            Principal principal
    ) {

        GetUserByIdResponse user_uuid = userService.getUserById(principal.getName());
        CreateOrderResponse createOrderResponse = orderService.createOrder(user_uuid);
        return ResponsePayloadBuilder.ok(createOrderResponse);
    }

    @Operation(summary = "Show all Orders")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    getAllOrders() {

        List<GetAllOrderResponse> getAllOrderResponses = orderService.getAllOrders();
        return ResponsePayloadBuilder.ok(getAllOrderResponses);
    }

    @Operation(summary = "Get Order by ID")
    @GetMapping(path = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    getOrderById(
            @PathVariable(name = "orderId") String uuid
    ) {

        GetOrderByIdResponse orderById = orderService.getOrderById(uuid);
        return ResponsePayloadBuilder.ok(orderById);
    }

    @Operation(summary = "Update Order by ID")
    @PutMapping(path = "/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Deprecated
    public ResponseEntity<?>
    updateOrderById(
            @PathVariable(name = "orderId") String uuid,
            @RequestBody Set<String> orderItemsUUID
    ) {

        if (true) {
            return ResponsePayloadBuilder.err("Deprecated", HttpStatus.FORBIDDEN, 400);
        }

        GetOrderResponse getOrderResponse = orderService.updateOrderById(uuid, orderItemsUUID);
        return ResponsePayloadBuilder.ok(getOrderResponse);
    }

    @Operation(summary = "Delete Order by ID")
    @DeleteMapping(path = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    deleteOrder(
            @PathVariable(name = "orderId") String uuid
    ) {

        Boolean isSuccess = orderService.deleteOrder(uuid);
        return ResponsePayloadBuilder.ok(isSuccess);
    }

    @Operation(summary = "Get Order Items by Order ID")
    @GetMapping(path = "/{orderId}/order-items", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    getOrderItemsByOrderId(
            @PathVariable(name = "orderId") String uuid
    ) {

        List<GetOrderItemResponse> orderItemsByOrderId = orderService.getOrderItemsByOrderId(uuid);
        return ResponsePayloadBuilder.ok(orderItemsByOrderId);

    }

}
