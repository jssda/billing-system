package org.team.cuc.billingsystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team.cuc.billingsystem.bean.bo.DepositWithdrawalBo;
import org.team.cuc.billingsystem.bean.vo.PageVO;
import org.team.cuc.billingsystem.po.transaction.DepositWithdrawalPo;
import org.team.cuc.billingsystem.service.DepositWithdrawalService;

import javax.validation.constraints.NotNull;

/**
 * @author jssd
 */
@Api(tags = {"充值提现"})
@RestController
@RequestMapping("/deposit-withdrawal")
public class DepositWithdrawalController {

    private final DepositWithdrawalService depositWithdrawalService;

    public DepositWithdrawalController(DepositWithdrawalService depositWithdrawalService) {
        this.depositWithdrawalService = depositWithdrawalService;
    }

    @PostMapping
    @ApiOperation(value = "增加一条流水")
    public DepositWithdrawalPo saveRecord(@RequestBody @NotNull(message = "data is null") DepositWithdrawalPo depositWithdrawalPo) {
        return depositWithdrawalService.save(depositWithdrawalPo);
    }

    @PostMapping("/find")
    @ApiOperation(value = "分页条件查询充值/提现信息")
    public PageVO<DepositWithdrawalPo> find(@RequestBody @NotNull(message = "data is null") DepositWithdrawalBo depositWithdrawalBo) {
        return depositWithdrawalService.find(depositWithdrawalBo);
    }

}
