package io.dolphin.mallorder.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private String orderId;
    private String userId;
    private LocalDateTime orderTime;
    private BigDecimal totalAmount;
    /**
     * 订单状态
     * 0:待付款
     * 1:已付款
     * 2:已发货
     * 3:已完成
     * 4:已取消
     */
    private Integer orderStatus;
    private String paymentMethod;
    private String shippingAddress;
    private String contactPhone;
    private List<OrderDetail> orderDetails;
}