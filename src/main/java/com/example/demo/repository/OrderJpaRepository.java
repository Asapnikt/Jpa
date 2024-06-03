package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    @Query(value = "select (select name from users where orders.user_id = users.user_id), count( service_id) from orders group by user_id",
            nativeQuery = true)
    List<Map<String, Long>> findByGrouping();
}
