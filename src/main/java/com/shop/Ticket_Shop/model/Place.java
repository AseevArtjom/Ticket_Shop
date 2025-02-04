package com.shop.Ticket_Shop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Places")
public class Place
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;
}
