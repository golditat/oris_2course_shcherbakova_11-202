package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.OrderDTO;
import org.example.entity.OrderEntity;
import org.example.repositories.GoodRepository;
import org.example.repositories.OrderRepository;
import org.example.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private OrderRepository repository;
    private final GoodRepository goodRepository;
    private final UserRepository userRepository;
    @Override
    public void addNewOrder(OrderDTO order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .id(order.getId())
                .user(userRepository.getReferenceById((long) order.getUserId()))
                .good(goodRepository.getReferenceById((long) order.getGoodId()))
                .createDate(order.getCreateDate())
                .build();
        repository.save(orderEntity);
    }

    @Override
    public OrderDTO getOrder(Long id) {
        OrderEntity orderEntity = repository.getOne(id);
        return OrderDTO.builder()
                .id(orderEntity.getId())
                .userId(Math.toIntExact(orderEntity.getUser().getId()))
                .goodId(Math.toIntExact(orderEntity.getGood().getId()))
                .createDate(orderEntity.getCreateDate())
                .build();
    }
    @Override
    public List<String> viewOrders() {
        List<String> orderDTOs = new ArrayList<>();
        List<OrderEntity> orderEntities = repository.findAll();
        for (OrderEntity user : orderEntities) {
            Long userId = user.getId();
            orderDTOs.add(getOrderView(userId));
        }
        return orderDTOs;
    }
    @Override
    public String getOrderView(Long id) {
        OrderDTO orderDto = getOrder(id);
        return String.format("CreateDate: %s,  userId: %s, goodId: %s",
                orderDto.getCreateDate(),
                orderDto.getUserId(),
                orderDto.getGoodId());
    }
}
