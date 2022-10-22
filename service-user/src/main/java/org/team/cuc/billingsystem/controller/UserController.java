package org.team.cuc.billingsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team.cuc.billingsystem.po.userservice.UserPo;
import org.team.cuc.billingsystem.service.UserService;

import javax.validation.constraints.NotEmpty;
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

    @GetMapping("/token/{token}")
    public UserPo getUserInfo(@NotEmpty(message = "token is null") @PathVariable String token) {
        return userService.getUserInfo(token);
    }

    @GetMapping("/id/{id}")
    public UserPo getUserById(@NotEmpty(message = "id is null") @PathVariable Integer id) {
        return userService.getUserById(id);
    }

}
