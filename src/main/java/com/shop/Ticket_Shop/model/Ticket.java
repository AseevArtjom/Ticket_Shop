package com.shop.Ticket_Shop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "Tickets")
@ToString(exclude = {"event"})
@EqualsAndHashCode(exclude = {"event"})
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "status")
    private Status status;

    @Column(name = "number")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}

