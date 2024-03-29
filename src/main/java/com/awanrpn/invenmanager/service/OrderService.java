package com.awanrpn.invenmanager.service;

import com.awanrpn.invenmanager.mapper.DTOMapper;
import com.awanrpn.invenmanager.mapper.OrderItemMapper;
import com.awanrpn.invenmanager.mapper.OrderMapper;
import com.awanrpn.invenmanager.model.dto.order.CreateOrderResponse;
import com.awanrpn.invenmanager.model.dto.order.GetAllOrderResponse;
import com.awanrpn.invenmanager.model.dto.order.GetOrderByIdResponse;
import com.awanrpn.invenmanager.model.dto.order.GetOrderResponse;
import com.awanrpn.invenmanager.model.dto.orderItem.GetOrderItemResponse;
import com.awanrpn.invenmanager.model.dto.user.GetUserByIdResponse;
import com.awanrpn.invenmanager.model.entity.Order;
import com.awanrpn.invenmanager.model.entity.OrderItem;
import com.awanrpn.invenmanager.repository.OrderItemRepository;
import com.awanrpn.invenmanager.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper = DTOMapper.ORDER_MAPPER;
    private final OrderItemMapper orderItemMapper = DTOMapper.ORDER_ITEM_MAPPER;

    @Transactional()
    public CreateOrderResponse
    createOrder(GetUserByIdResponse user) {

        //Check
        // With Principal
        //Find User
        //FInd email


        Order order = new Order();
        /* User Detail Set */
        order.setUserid(user.id());
        order.setCustomerEmail(user.email());
        order.setCustomerName(user.name());

        /* Orchestration */
        order.setTotalPrice(BigInteger.ZERO);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.createOrderResponse(savedOrder);
    }

    @Transactional(readOnly = true)
    public List<GetAllOrderResponse>
    getAllOrders() {

        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(orderMapper::getAllOrderResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public GetOrderByIdResponse
    getOrderById(String uuid) {

        Order order = orderRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("ID Order Tidak Ditemukan"));

        return orderMapper.getOrderByIdResponse(order);
    }

    @Transactional
    public GetOrderResponse
    updateOrderById(
            String uuid,
            Set<String> orderItemsUUID
    ) {

        Order orderPreSaved = orderRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("ID Order Tidak Ditemukan"));

        /* Dapatkan Order Item dan kosongkan */
        Set<OrderItem> orderItemsPreSaved = orderPreSaved.getOrderItems();
        orderItemsPreSaved.clear();

        /* Masukan Order Item baru */
        orderItemsUUID.forEach((orderItemUUID -> {
            //Dapatkan OrderItem by ID
            OrderItem orderItem = orderItemRepository
                    .findById(orderItemUUID)
                    .orElseThrow(() -> new IllegalArgumentException("ID OrderItem Tidak Ditemukan"));

            //Push ke existing
            orderItemsPreSaved.add(orderItem);
        }
        ));

        Order orderSaved = orderRepository.saveAndFlush(orderPreSaved);
        orderPreSaved.refreshTotalPrice();

        return orderMapper.getOrderResponse(orderSaved);
    }

    @Transactional()
    public Boolean
    deleteOrder(String uuid) {

        try {

            Order orderInDB = orderRepository.findById(uuid)
                    .orElseThrow(() -> new IllegalArgumentException("Order Id tidak ditemukan"));

            orderRepository.deleteById(uuid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;

    }

    /* External Module */
    public List<GetOrderItemResponse>
    getOrderItemsByOrderId(
            @PathVariable(name = "orderId") String uuid
    ) {

        Order order = orderRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("ID Order Tidak Ditemukan"));

        List<OrderItem> allByOrder = orderItemRepository.findAllByOrder(order);

        return allByOrder.stream()
                .map(orderItemMapper::getOrderItemResponse)
                .toList();
    }
}
