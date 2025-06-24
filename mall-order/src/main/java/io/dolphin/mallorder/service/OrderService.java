package io.dolphin.mallorder.service;

import io.dolphin.mallorder.entity.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getOrders();

    public Order getOrderById(String orderId);

    public List<Order> getOrdersByUserId(String userId);

    public boolean cancelOrder(String orderId);
}