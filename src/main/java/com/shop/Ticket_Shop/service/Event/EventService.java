package com.shop.Ticket_Shop.service.Event;

import com.shop.Ticket_Shop.model.Event;

import java.util.List;

public interface EventService
{
    void save(Event event);

    int[] saveEventsList(List<Event> events);

    void update(Event event);

    void delete(Event event);

    List<Event> findAll();

    void deleteAll();

}
