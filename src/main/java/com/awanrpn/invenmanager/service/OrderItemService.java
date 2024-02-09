package com.awanrpn.invenmanager.service;

import com.awanrpn.invenmanager.mapper.DTOMapper;
import com.awanrpn.invenmanager.mapper.OrderItemMapper;
import com.awanrpn.invenmanager.model.dto.orderItem.CreateOrderItemResponse;
import com.awanrpn.invenmanager.model.dto.orderItem.GetOrderItemResponse;
import com.awanrpn.invenmanager.model.dto.orderItem.OrderItemRequest;
import com.awanrpn.invenmanager.model.entity.Order;
import com.awanrpn.invenmanager.model.entity.OrderItem;
import com.awanrpn.invenmanager.model.entity.Product;
import com.awanrpn.invenmanager.repository.OrderItemRepository;
import com.awanrpn.invenmanager.repository.OrderRepository;
import com.awanrpn.invenmanager.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemMapper orderItemMapper = DTOMapper.ORDER_ITEM_MAPPER;

    @Transactional(timeout = 2)
    public CreateOrderItemResponse
    createOrderItem(OrderItemRequest orderItemRequest) {

        String orderUUID = orderItemRequest.orderUUID();
        String productUUID = orderItemRequest.productUUID();
        Integer quantity = orderItemRequest.quantity();

        Order order = orderRepository.findById(orderUUID)
                .orElseThrow(() -> new IllegalArgumentException("ID Order Tidak Ditemukan"));

        Product product = productRepository.findById(productUUID)
                .orElseThrow(() -> new IllegalArgumentException("ID Product Tidak Ditemukan"));

        OrderItem orderItem = new OrderItem();

        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setUnitPrice(
                product.getPrice()
                        .multiply(BigInteger.valueOf(quantity.longValue()))
        );

        OrderItem orderItemInDB = orderItemRepository.save(orderItem);

        return orderItemMapper.createOrderItemResponse(orderItemInDB);
    }

    @Transactional(readOnly = true, timeout = 2)
    public List<GetOrderItemResponse>
    getAllOrderItems() {

        List<OrderItem> orderItems = orderItemRepository.findAll();

        return orderItems.stream()
                .map(orderItemMapper::getOrderItemResponse).toList();
    }

    @Transactional(readOnly = true)
    public GetOrderItemResponse
    getOrderItemById(
            @PathVariable(name = "orderItemId") String uuid
    ) {

        OrderItem orderItem = orderItemRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("ID Order Tidak Ditemukan"));

        return orderItemMapper.getOrderItemResponse(orderItem);
    }

    public GetOrderItemResponse
    updateOrderItemById(
            String uuid,
            OrderItemRequest orderItemRequest
    ) {

        String orderUUID = orderItemRequest.orderUUID();
        String productUUID = orderItemRequest.productUUID();
        Integer quantity = orderItemRequest.quantity();

        /* Check Order Item */
        OrderItem orderItem = orderItemRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("ID Order Item Tidak Ditemukan"));

        /* Check Order */
        Order order = orderRepository.findById(orderUUID)
                .orElseThrow(() -> new IllegalArgumentException("ID Order Tidak Ditemukan"));

        /* Check Products */
        Product product = productRepository.findById(productUUID)
                .orElseThrow(() -> new IllegalArgumentException("ID Product Tidak Ditemukan"));

        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);

        orderItem.setUnitPrice(
                product.getPrice()
                        .multiply(BigInteger.valueOf(quantity.longValue()))
        );

        OrderItem orderItemInDB = orderItemRepository.save(orderItem);
        return orderItemMapper.getOrderItemResponse(orderItemInDB);
    }

}
