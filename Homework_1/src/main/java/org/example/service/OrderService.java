package org.example.service;

import org.example.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    void addNewOrder(OrderDTO order);

    OrderDTO getOrder(Long id);

    String getOrderView(Long id);
    List<String> viewOrders();
}
