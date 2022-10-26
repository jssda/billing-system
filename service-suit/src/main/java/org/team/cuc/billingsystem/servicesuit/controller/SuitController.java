package org.team.cuc.billingsystem.servicesuit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.team.cuc.billingsystem.po.suitservice.SuitPo;
import org.team.cuc.billingsystem.servicesuit.suitMapper.SuitMapper;

@RestController
public class SuitController {
    @Autowired
    SuitMapper suitMapper;

    @GetMapping("/listSuit")
    public String listSuit() {
        return suitMapper.selectAllSuits().toString();
    }

    @GetMapping("/insertSuit")
    public String insert() {
        SuitPo suitPo = new SuitPo();
        suitPo.setSuitType(2);
        suitPo.setName("测试");
        suitPo.setCostType(1);
        suitPo.setPrice(1.1);
        suitPo.setStatus(2);
        suitPo.setCreateUid(123);
        suitMapper.saveOne(suitPo);
        return "成功";
    }

    @GetMapping("/updateSuit")
    public String update() {
        SuitPo suitPo = new SuitPo();
        suitPo.setSuitType(11111);
        suitPo.setName("测试4563333333333");
        suitPo.setId(2);
        suitMapper.updateById(suitPo);
        return "成功";
    }

    @GetMapping("/selectById")
    public String selectById() {
        SuitPo suitPo = new SuitPo();
        return  suitMapper.selectSuitById(1).toString();
    }

    @RequestMapping("/select")
    @ResponseBody
    public String selectSuit(SuitPo suitPo) {
        System.out.println(suitPo.getId());
        return  suitMapper.selectSuit(suitPo).toString();
    }


}
