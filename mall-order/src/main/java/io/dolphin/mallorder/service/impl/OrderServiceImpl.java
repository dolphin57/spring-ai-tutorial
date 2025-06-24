package io.dolphin.mallorder.service.impl;

import io.dolphin.mallorder.entity.Order;
import io.dolphin.mallorder.entity.OrderDetail;
import io.dolphin.mallorder.mapper.OrderMapper;
import io.dolphin.mallorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public List<Order> getOrders() {
        List<Order> orders = orderMapper.selectAllOrders();
        for (Order order : orders) {
            List<OrderDetail> details = orderMapper.selectOrderDetailsByOrderId(order.getOrderId());
            order.setOrderDetails(details);
        }
        return orders;
    }

    public Order getOrderById(String orderId) {
        Order order = orderMapper.selectOrderById(orderId);
        if (order != null) {
            List<OrderDetail> details = orderMapper.selectOrderDetailsByOrderId(orderId);
            order.setOrderDetails(details);
        }
        return order;
    }

    public List<Order> getOrdersByUserId(String userId) {
        List<Order> orders = orderMapper.selectOrdersByUserId(userId);
        for (Order order : orders) {
            List<OrderDetail> details = orderMapper.selectOrderDetailsByOrderId(order.getOrderId());
            order.setOrderDetails(details);
        }
        return orders;
    }

    public boolean cancelOrder(String orderId) {
        return orderMapper.cancelOrder(orderId) > 0;
    }
}
