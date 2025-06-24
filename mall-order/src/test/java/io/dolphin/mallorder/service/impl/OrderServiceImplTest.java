package io.dolphin.mallorder.service.impl;

import io.dolphin.mallorder.entity.Order;
import io.dolphin.mallorder.entity.OrderDetail;
import io.dolphin.mallorder.mapper.OrderMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {
    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    private static final String VALID_ORDER_ID = "ORD20250414001";
    private static final String INVALID_ORDER_ID = "nonexistent";

    // 构建测试用订单数据
    private Order buildTestOrder() {
        Order order = new Order();
        order.setOrderId(VALID_ORDER_ID);
        order.setUserId("user123");
        order.setTotalAmount(new BigDecimal("99.9"));
        order.setOrderTime(LocalDateTime.now());
        return order;
    }

    @DisplayName("TC001: 验证有效订单ID的完整数据获取")
    @Test
    void testGetOrderById_WithValidId() {
        // 准备测试数据
        Order expectedOrder = buildTestOrder();

        // 配置mock行为
        when(orderMapper.selectOrderById(VALID_ORDER_ID)).thenReturn(expectedOrder);

        // 执行测试
        Order actualOrder = orderService.getOrderById(VALID_ORDER_ID);

        // 验证结果
        assertNotNull(actualOrder, "返回的订单对象不应为空");
        assertEquals(expectedOrder.getOrderId(), actualOrder.getOrderId(), "订单ID不匹配");
        assertNotNull(actualOrder.getOrderDetails(), "订单明细不应为空");

        // 验证交互次数
//        verify(orderMapper, times(1)).selectOrderById(VALID_ORDER_ID);
//        verify(orderMapper, times(1)).selectOrderDetailsByOrderId(VALID_ORDER_ID);
    }
}