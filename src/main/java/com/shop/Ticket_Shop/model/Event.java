package com.shop.Ticket_Shop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "Events")
@ToString(exclude = {"tickets"})
@EqualsAndHashCode(exclude = {"tickets"})
public class Event
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "event_date")
    private LocalDate event_date;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}