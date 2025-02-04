package com.shop.Ticket_Shop.dao;

import com.shop.Ticket_Shop.model.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role,Integer>
{
    Role findByRoleName(String roleName);
}
