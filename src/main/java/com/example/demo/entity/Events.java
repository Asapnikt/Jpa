package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;

@Getter
@Setter
@Entity(name = "events")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "startDate")
    private Calendar startDate;
    @Column(name = "finishDate")
    private Calendar finishDate;
    @Column(name = "description")
    private String description;
}
