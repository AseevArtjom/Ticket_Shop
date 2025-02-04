package com.shop.Ticket_Shop.service.Event;

import com.shop.Ticket_Shop.dao.EventRepository;
import com.shop.Ticket_Shop.dao.PlaceRepository;
import com.shop.Ticket_Shop.dao.TicketRepository;
import com.shop.Ticket_Shop.dto.EventCreationDto;
import com.shop.Ticket_Shop.dto.TicketPackDto;
import com.shop.Ticket_Shop.model.Event;
import com.shop.Ticket_Shop.model.Place;
import com.shop.Ticket_Shop.model.Status;
import com.shop.Ticket_Shop.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService
{
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PlaceRepository placeRepository;

    public void createEvent(EventCreationDto eventCreationDto){
        Place place = placeRepository.findByName(eventCreationDto.getPlace().getName());
        if(place == null){
            place = new Place();
            place.setName(eventCreationDto.getPlace().getName());
            place.setAddress(eventCreationDto.getPlace().getAddress());
            place = placeRepository.save(place);
        }

        Event event = new Event();
        event.setEvent_date(eventCreationDto.getEvent_Date());
        event.setName(eventCreationDto.getName());
        event.setPlace(place);

        event = eventRepository.save(event);

        for (TicketPackDto ticketPackDto : eventCreationDto.getTicketPack()){
            for (int i = 0; i < ticketPackDto.getCount(); i++){
                Ticket ticket = new Ticket();
                ticket.setCost(ticketPackDto.getCost());
                ticket.setStatus(Status.FREE);
                ticket.setEvent(event);
                ticketRepository.save(ticket);
            }
        }
    }

    @Override
    public List<Event> findAll() { return eventRepository.findAll(); }

    @Override
    public void delete(Event event) { eventRepository.delete(event); }

    @Override
    public int[] saveEventsList(List<Event> events) {
        eventRepository.saveAll(events);
        return new int[1];
    }

    @Override
    public void save(Event event) { eventRepository.save(event); }

    @Override
    public void update(Event event) { eventRepository.save(event); }

    @Override
    public void deleteAll() { eventRepository.deleteAll(); }
}
