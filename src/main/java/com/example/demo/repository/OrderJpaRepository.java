package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    @Query(value = "select execution_date , count(1)   from orders group by execution_date",
            nativeQuery = true)
   List<Object[]>  findByGrouping();

    Order findByUser_Id(long id);
}
