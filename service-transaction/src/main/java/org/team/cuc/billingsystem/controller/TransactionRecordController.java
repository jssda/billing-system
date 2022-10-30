package org.team.cuc.billingsystem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.team.cuc.billingsystem.bean.bo.AjaxResponse;
import org.team.cuc.billingsystem.bean.bo.RecordBo;
import org.team.cuc.billingsystem.bean.vo.PageVO;
import org.team.cuc.billingsystem.po.transaction.TransactionRecordPo;
import org.team.cuc.billingsystem.service.TransactionRecordService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author jssd
 */
@Api(tags = {"交易流水"})
@RestController
@RequestMapping("/transactions")
public class TransactionRecordController {

    private final TransactionRecordService transactionRecordService;

    public TransactionRecordController(TransactionRecordService transactionRecordService) {
        this.transactionRecordService = transactionRecordService;
    }

    @GetMapping()
    public AjaxResponse<List<TransactionRecordPo>> listRecords() {
        return AjaxResponse.success(transactionRecordService.listRecords());
    }

    @PostMapping
    @ApiOperation(value = "增加一条流水")
    public AjaxResponse<TransactionRecordPo> saveRecord(@RequestBody @NotNull(message = "data is null") TransactionRecordPo recordPo) {
        return AjaxResponse.success(transactionRecordService.saveRecord(recordPo));
    }

    @PostMapping("/find")
    @ApiOperation(value = "分页查询流水信息")
    public AjaxResponse<PageVO<TransactionRecordPo>> find(@RequestBody @NotNull(message = "data is null") RecordBo recordBo) {
        return AjaxResponse.success(transactionRecordService.find(recordBo));
    }
}
