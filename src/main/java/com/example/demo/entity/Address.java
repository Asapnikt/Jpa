package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "cityName")
    private String city;
    @Column(name = "countryName")
    private String country;
    @Column(name ="streetName")
    private String street;
    @Column(name = "houseNumber")
    private String houseNumber;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
