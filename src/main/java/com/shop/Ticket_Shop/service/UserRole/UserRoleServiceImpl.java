package com.shop.Ticket_Shop.service.UserRole;

import com.shop.Ticket_Shop.dao.UserRoleRepository;
import com.shop.Ticket_Shop.model.AppUser;
import com.shop.Ticket_Shop.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService
{
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> findAll() { return userRoleRepository.findAll(); }

    @Override
    public void delete(UserRole userRole) { userRoleRepository.delete(userRole); }

    @Override
    public int[] saveEventsList(List<UserRole> userRoles) {
        userRoleRepository.saveAll(userRoles);
        return new int[1];
    }

    @Override
    public void save(UserRole userRole) { userRoleRepository.save(userRole); }

    @Override
    public void update(UserRole userRole) { userRoleRepository.save(userRole); }

    @Override
    public void deleteAll() { userRoleRepository.deleteAll(); }
}
