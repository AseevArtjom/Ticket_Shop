package com.shop.Ticket_Shop.dao;

import com.shop.Ticket_Shop.model.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<AppUser,Integer>
{
    @Query("from AppUser a where a.email = ?1")
    Optional<AppUser> findUserAccount(String email);

    boolean existsByUserName(String username);
    boolean existsByEmail(String email);
}
