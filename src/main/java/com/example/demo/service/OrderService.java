package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderJpaRepository orderJpaRepository;

    @Autowired
    public void setOrderJpaRepository(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }


    public void saveEvents(Order order) {
        orderJpaRepository.save(order);
    }

    public void editEvents(Order order) {
        orderJpaRepository.save(order);
    }

    public void deleteEvents(Order order) {
        orderJpaRepository.delete(order);
    }

    public Order getEvent(long id) {
        return orderJpaRepository.findById(id).get();
    }

    public List<Order> getAllEvents() {
        return orderJpaRepository.findAll();
    }

    public void getValueByGrouping() {
        orderJpaRepository.findByGrouping();
    }
}
