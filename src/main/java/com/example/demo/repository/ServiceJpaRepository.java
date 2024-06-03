package com.example.demo.repository;

import com.example.demo.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceJpaRepository extends JpaRepository<Service, Long> {
}