package com.shop.Ticket_Shop.controller;

import com.shop.Ticket_Shop.model.Event;
import com.shop.Ticket_Shop.service.Event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class EventController
{
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/Event",method = RequestMethod.GET)
    public String EventPage(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        return "event/main";
    }

    @GetMapping(value = "/Event/Create")
    public String CreateEventGET(){
        return "event/create";
    }
}