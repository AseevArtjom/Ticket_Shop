package com.shop.Ticket_Shop.service.Role;

import com.shop.Ticket_Shop.model.Role;

import java.util.List;

public interface RoleService
{
    void save(Role role);

    int[] saveEventsList(List<Role> role);

    void update(Role role);

    void delete(Role role);

    List<Role> findAll();

    void deleteAll();

    Role findByRoleName(String roleName);
}
