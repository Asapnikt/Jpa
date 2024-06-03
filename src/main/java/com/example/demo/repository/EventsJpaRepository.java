package com.example.demo.repository;

import com.example.demo.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsJpaRepository extends JpaRepository<Events, Long> {
}
