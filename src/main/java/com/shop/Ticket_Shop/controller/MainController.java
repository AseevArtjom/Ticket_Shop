package com.shop.Ticket_Shop.controller;

import com.shop.Ticket_Shop.model.AppUser;
import com.shop.Ticket_Shop.model.Role;
import com.shop.Ticket_Shop.model.UserRole;
import com.shop.Ticket_Shop.service.Role.RoleService;
import com.shop.Ticket_Shop.service.User.UserService;
import com.shop.Ticket_Shop.service.UserRole.UserRoleService;
import com.shop.Ticket_Shop.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    public MainController(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/")
    public String loginPage(Model model) {
        return "loginPage";
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtil.pageInfoOutputMessageCreator(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "adminPage";
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("error", error);
        return "loginPage";
    }

    @PostMapping(value = "/login")
    public String loginPage(Model model, Authentication authentication) {
        if (authentication != null) {
            System.out.println("User " + authentication.getName() + " is authenticated!");
        } else {
            System.out.println("Authentication failed.");
        }
        return "/main";
    }

    @GetMapping(value = "/Main")
    public String mainPage(Model model) {
        return "main";
    }

    @GetMapping(value = "/register")
    public String registerPage(Model model) {
        return "registerPage";
    }

    @PostMapping(value = "/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String phone,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               Model model) {

        if (username == null || email == null || phone == null || password == null || confirmPassword == null ||
                username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "All fields are required.");
            return "registerPage";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "registerPage";
        }

        if (userService.existsByUsername(username)) {
            model.addAttribute("error", "Username already exists.");
            return "registerPage";
        }

        if (userService.existsByEmail(email)) {
            model.addAttribute("error", "Email already exists.");
            return "registerPage";
        }

        if (!phone.matches("\\+?\\d{10,15}")) {
            model.addAttribute("error", "Invalid phone number.");
            return "registerPage";
        }

        AppUser newUser = new AppUser();
        newUser.setUserName(username);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setEncryptedPassword(passwordEncoder.encode(password));
        newUser.setEnabled(true);

        userService.save(newUser);

        Role userRole = roleService.findByRoleName("USER");

        if (userRole == null) {
            userRole = new Role();
            userRole.setRoleName("USER");
            roleService.save(userRole);
        }

        UserRole userRoleMapping = new UserRole();
        userRoleMapping.setUser(newUser);
        userRoleMapping.setRole(userRole);
        userRoleService.save(userRoleMapping);

        model.addAttribute("success", "Registration successful!");
        return "loginPage";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfullPage(Model model) {
        model.addAttribute("title", "Logout");
        return "LogoutSuccessfullPage";
    }

    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal) {
        String userName = principal.getName();
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtil.pageInfoOutputMessageCreator(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("userName", loginedUser.getUsername());

        return "userInfo";
    }
}
