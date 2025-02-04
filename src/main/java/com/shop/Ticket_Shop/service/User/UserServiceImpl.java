package com.shop.Ticket_Shop.service.User;

import com.shop.Ticket_Shop.dao.EventRepository;
import com.shop.Ticket_Shop.dao.PlaceRepository;
import com.shop.Ticket_Shop.dao.TicketRepository;
import com.shop.Ticket_Shop.dao.UserRepository;
import com.shop.Ticket_Shop.dto.EventCreationDto;
import com.shop.Ticket_Shop.dto.TicketPackDto;
import com.shop.Ticket_Shop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AppUser> findAll() { return userRepository.findAll(); }

    @Override
    public void delete(AppUser user) { userRepository.delete(user); }

    @Override
    public int[] saveEventsList(List<AppUser> users) {
        userRepository.saveAll(users);
        return new int[1];
    }

    @Override
    public void save(AppUser user) { userRepository.save(user); }

    @Override
    public void update(AppUser user) { userRepository.save(user); }

    @Override
    public void deleteAll() { userRepository.deleteAll(); }

    @Override
    public Boolean existsByUsername(String username) { return userRepository.existsByUserName(username); }

    @Override
    public Boolean existsByEmail(String email) { return userRepository.existsByEmail(email); }
}
