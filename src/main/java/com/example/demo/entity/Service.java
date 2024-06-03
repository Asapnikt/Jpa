package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "service")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    @Id
    @Column(name = "service_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private float cost;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "service")
    List<Order> orderList;
}
