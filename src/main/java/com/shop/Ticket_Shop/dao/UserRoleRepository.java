package com.shop.Ticket_Shop.dao;

import com.shop.Ticket_Shop.model.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRole,Integer>
{
    @Query("Select ur.role.roleName from UserRole ur where ur.user.user_id = ?1")
    List<String> getRoleNames(Integer userId);
}
