package org.team.cuc.billingsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team.cuc.billingsystem.po.userservice.UserPo;
import org.team.cuc.billingsystem.service.UserService;

import java.util.List;

/**
 * @author jssd
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserPo> listUsers() {
        return userService.listUsers();
    }

}
