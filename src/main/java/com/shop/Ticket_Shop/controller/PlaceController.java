package com.shop.Ticket_Shop.controller;

import com.shop.Ticket_Shop.dao.PlaceRepository;
import com.shop.Ticket_Shop.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PlaceController
{
    @Autowired
    private PlaceRepository placeRepository;

    @GetMapping(value = "/Place")
    public String PlacePage(Model model) {
        List<Place> places = placeRepository.findAll();
        model.addAttribute("places",places);
        return "place/main";
    }

    @GetMapping(value = "/Place/Create")
    public String CreatePlaceGET(){
        return "place/create";
    }

    @PostMapping(value = "/Place/Create")
    public String CreatePlacePOST(@RequestParam("PlaceName") String PlaceName,
                                  @RequestParam("PlaceAddress") String PlaceAddress,
                                  Model model) {
        Place addPlace = new Place();
        addPlace.setName(PlaceName);
        addPlace.setAddress(PlaceAddress);
        placeRepository.save(addPlace);

        return "redirect:/Place";
    }

    @PostMapping("/Place/Delete/{id}")
    public String deletePlace(@PathVariable Integer id) {
        placeRepository.deleteById(id);
        return "redirect:/Place";
    }

    @GetMapping("/Place/Edit/{id}")
    public String editPlace(@PathVariable Integer id, Model model) {
        Place place = placeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid place ID: " + id));
        model.addAttribute("place", place);
        return "place/edit";
    }

    @PostMapping("/Place/Update/{id}")
    public String updatePlace(@PathVariable Integer id, @RequestParam String PlaceName, @RequestParam String PlaceAddress) {
        Place place = placeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid place ID: " + id));
        place.setName(PlaceName);
        place.setAddress(PlaceAddress);
        placeRepository.save(place);
        return "redirect:/Place";
    }



}
