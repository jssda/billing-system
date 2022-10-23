package org.team.cuc.billingsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team.cuc.billingsystem.po.transaction.TransactionRecordPo;
import org.team.cuc.billingsystem.service.TransactionRecordService;

import java.util.List;

/**
 * @author jssd
 */
@RestController
@RequestMapping("/transactions")
public class TransactionRecordController {

    private final TransactionRecordService transactionRecordService;

    public TransactionRecordController(TransactionRecordService transactionRecordService) {
        this.transactionRecordService = transactionRecordService;
    }

    @GetMapping()
    public List<TransactionRecordPo> listRecords() {
        return transactionRecordService.listRecords();
    }

}
