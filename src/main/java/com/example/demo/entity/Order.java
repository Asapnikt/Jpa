package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "executionDate")
    private Date executionDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="service_id", nullable=false)
    private Service service;

}
