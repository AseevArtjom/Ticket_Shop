package com.shop.Ticket_Shop.service.UserDetailsService;

import com.shop.Ticket_Shop.dao.UserRepository;
import com.shop.Ticket_Shop.dao.UserRoleRepository;
import com.shop.Ticket_Shop.model.AppUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.userRepository.findUserAccount(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User " + userName + " was not found in the database"));

        log.debug("Found User: " + appUser);

        List<String> roleNames = this.userRoleRepository.getRoleNames(appUser.getUser_id());

        if (roleNames == null || roleNames.isEmpty()) {
            log.error("User has no roles assigned: " + userName);
            throw new UsernameNotFoundException("User " + userName + " has no roles assigned.");
        }

        List<GrantedAuthority> grantList = roleNames.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        return new User(appUser.getUserName(), appUser.getEncryptedPassword(), grantList);
    }

}
