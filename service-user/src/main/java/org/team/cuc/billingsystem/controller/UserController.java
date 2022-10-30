package org.team.cuc.billingsystem.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.team.cuc.billingsystem.bean.bo.AjaxResponse;
import org.team.cuc.billingsystem.bean.dto.UserAmountDto;
import org.team.cuc.billingsystem.po.userservice.UserPo;
import org.team.cuc.billingsystem.service.UserService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @PostMapping("/saveOne")
    public AjaxResponse<UserPo> saveOne(@RequestBody @NotNull UserPo userPo) {
        return AjaxResponse.success(userService.saveUser(userPo));
    }


    @GetMapping
    public List<UserPo> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/token/{token}")
    public AjaxResponse<UserPo> getUserInfo(@NotEmpty(message = "token is null") @PathVariable String token) {
        return AjaxResponse.success(userService.getUserInfo(token));
    }

    @GetMapping("/id/{id}")
    public AjaxResponse<UserPo> getUserById(@NotEmpty(message = "id is null") @PathVariable Integer id) {
        return AjaxResponse.success(userService.getUserById(id));
    }

    @ApiOperation("获取用户余额")
    @GetMapping("/getUserAmount")
    public AjaxResponse<UserAmountDto> getUserAmount(@NotNull Integer userId) {
        UserAmountDto userAmountDto = userService.getUserAmountDto(userId);
        return AjaxResponse.success(userAmountDto);
    }

    @ApiOperation("更改用户余额")
    @PostMapping("/updateAmount")
    public AjaxResponse<Object> updateAmount(@RequestBody UserAmountDto userAmountDto) {
        userService.updateAmount(userAmountDto);
        return AjaxResponse.success();
    }

}
