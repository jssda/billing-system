package org.team.cuc.billingsystem.servicesuit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.team.cuc.billingsystem.bean.bo.AjaxResponse;
import org.team.cuc.billingsystem.po.suitservice.SuitPo;
import org.team.cuc.billingsystem.servicesuit.bean.bo.SuitBo;
import org.team.cuc.billingsystem.servicesuit.bean.bo.ToolBo;
import org.team.cuc.billingsystem.servicesuit.service.SuitService;
import org.team.cuc.billingsystem.servicesuit.suitMapper.SuitMapper;

import javax.validation.constraints.NotNull;

@Api(tags = {"套餐"})
@RestController
public class SuitController {
    @Autowired
    SuitService suitService;

    //pass
    @ApiOperation(value = "列出所有套餐")
    @PostMapping("/listSuit")
    public AjaxResponse listSuit(@NotNull(message = "data is null") SuitBo bo) {
        return AjaxResponse.success(suitService.listSuit(bo));
    }

    //pass
    @ApiOperation(value = "插入一个套餐")
    @PostMapping("/insertSuit")
    public AjaxResponse insert(@NotNull(message = "data is null") SuitBo bo) {
        return AjaxResponse.success(suitService.insertSuit(bo));
    }

    //pass
    @ApiOperation(value = "更新套餐数据")
    @PostMapping("/updateSuit")
    public AjaxResponse update(@NotNull(message = "data is null") SuitBo bo) {
        return AjaxResponse.success(suitService.updateSuit(bo));
    }

    //pass
    @ApiOperation(value = "条件查找套餐信息")
    @PostMapping("/selectSuit")
    public AjaxResponse selectSuit(@NotNull(message = "data is null") SuitBo bo) {
        return AjaxResponse.success(suitService.selectById(bo));
    }


}
