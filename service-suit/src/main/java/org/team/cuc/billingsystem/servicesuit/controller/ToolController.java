package org.team.cuc.billingsystem.servicesuit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.team.cuc.billingsystem.bean.bo.AjaxResponse;
import org.team.cuc.billingsystem.servicesuit.bean.bo.SuitBo;
import org.team.cuc.billingsystem.servicesuit.bean.bo.ToolBo;
import org.team.cuc.billingsystem.servicesuit.service.ToolService;
import org.team.cuc.billingsystem.servicesuit.suitMapper.SuitMapper;
import org.team.cuc.billingsystem.servicesuit.suitMapper.ToolMapper;

import javax.validation.constraints.NotNull;

/**
 * @Description: 组件
 * @Author: czq
 * @CreateTime: 2022-10-25 18:18
 */
@Api(tags = {"组件"})
@RestController
public class ToolController {
    @Autowired
    ToolMapper toolMapper;
    @Autowired
    ToolService toolService;

    @PostMapping("/listTool")
    public AjaxResponse listTool( @NotNull(message = "data is null") ToolBo bo) {
        return AjaxResponse.success(toolService.listTools(bo));
    }

    @GetMapping("/updateTool")
    public String updateSuit() {
        return toolMapper.selectAllTools().toString();
    }

    @GetMapping("/insertTool")
    public String insertTool() {
        return null;
    }

    @GetMapping("/refreshTool2db")
    public String refreshTool2db() {
       toolService.refreshTool2db();
       return "success";
    }

    @ApiOperation(value = "通过套餐列出所有组件")
    @PostMapping("/listToolFromSuit")
    public AjaxResponse listToolFromSuit( @NotNull(message = "data is null") SuitBo bo) {
        return AjaxResponse.success(toolService.listToolsFromSuit(bo));
    }
}
