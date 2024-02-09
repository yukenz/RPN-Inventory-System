package com.awanrpn.invenmanager.controller;


import com.awanrpn.invenmanager.model.dto.ResponsePayloadBuilder;
import com.awanrpn.invenmanager.model.dto.orderItem.CreateOrderItemResponse;
import com.awanrpn.invenmanager.model.dto.orderItem.GetOrderItemResponse;
import com.awanrpn.invenmanager.model.dto.orderItem.OrderItemRequest;
import com.awanrpn.invenmanager.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/order-items")
@RequiredArgsConstructor
@Tag(name = "Order Item Module")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Operation(summary = "Create OrderItem")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    createOrderItem(
            @RequestBody OrderItemRequest orderItemRequest
    ) {
        CreateOrderItemResponse createOrderItemResponse = orderItemService.createOrderItem(orderItemRequest);
        return ResponsePayloadBuilder.ok(createOrderItemResponse);
    }

    @Operation(summary = "Show all Order Items")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    getAllOrderItems() {

        List<GetOrderItemResponse> getOrderItemResponses = orderItemService.getAllOrderItems();

        return ResponsePayloadBuilder.ok(getOrderItemResponses);
    }

    @Operation(summary = "Get Order Item by ID")
    @GetMapping(path = "/{orderItemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    getOrderItemById(
            @PathVariable(name = "orderItemId") String uuid
    ) {
        GetOrderItemResponse getOrderItemResponse = orderItemService.getOrderItemById(uuid);
        return ResponsePayloadBuilder.ok(getOrderItemResponse);
    }

    @Operation(summary = "Update Order Item by ID")
    @PutMapping(path = "/{orderItemId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>
    updateOrderItemId(
            @PathVariable(name = "orderItemId") String uuid,
            @RequestBody OrderItemRequest orderItemRequest
    ) {

        GetOrderItemResponse updateOrderItemById = orderItemService.updateOrderItemById(uuid, orderItemRequest);
        return ResponsePayloadBuilder.ok(updateOrderItemById);
    }

}
