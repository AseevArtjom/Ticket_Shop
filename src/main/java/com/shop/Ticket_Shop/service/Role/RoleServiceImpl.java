package com.shop.Ticket_Shop.service.Role;

import com.shop.Ticket_Shop.dao.RoleRepository;
import com.shop.Ticket_Shop.model.AppUser;
import com.shop.Ticket_Shop.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() { return roleRepository.findAll(); }

    @Override
    public void delete(Role role) { roleRepository.delete(role); }

    @Override
    public int[] saveEventsList(List<Role> roles) {
        roleRepository.saveAll(roles);
        return new int[1];
    }

    @Override
    public void save(Role role) { roleRepository.save(role); }

    @Override
    public void update(Role role) { roleRepository.save(role); }

    @Override
    public void deleteAll() { roleRepository.deleteAll(); }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
