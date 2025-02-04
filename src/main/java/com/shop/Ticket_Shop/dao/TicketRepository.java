package com.shop.Ticket_Shop.dao;

import com.shop.Ticket_Shop.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer>
{

}
