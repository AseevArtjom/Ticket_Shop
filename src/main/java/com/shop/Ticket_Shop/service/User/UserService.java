package com.shop.Ticket_Shop.service.User;


import com.shop.Ticket_Shop.model.AppUser;
import com.shop.Ticket_Shop.model.Event;

import java.util.List;

public interface UserService
{
    void save(AppUser user);

    int[] saveEventsList(List<AppUser> user);

    void update(AppUser user);

    void delete(AppUser user);

    List<AppUser> findAll();

    void deleteAll();

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
