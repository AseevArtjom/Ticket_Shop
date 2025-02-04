package com.shop.Ticket_Shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "app_user")
@SequenceGenerator(name = "app_user_seq",sequenceName = "app_user_user_id_seq",allocationSize = 1)
public class AppUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "app_user_seq")
    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "encrypted_password")
    private String encryptedPassword;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        AppUser appUser = (AppUser) obj;
        return enabled == appUser.enabled
                && Objects.equals(user_id,appUser.user_id)
                && Objects.equals(userName,appUser.userName)
                && Objects.equals(encryptedPassword,appUser.encryptedPassword)
                && Objects.equals(email,appUser.email);
    }
}
