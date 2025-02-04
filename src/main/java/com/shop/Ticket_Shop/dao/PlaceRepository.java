package com.shop.Ticket_Shop.dao;

import com.shop.Ticket_Shop.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Integer>
{
    Place findByName(String name);
}
