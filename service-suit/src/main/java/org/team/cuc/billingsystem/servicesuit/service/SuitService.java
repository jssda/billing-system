package org.team.cuc.billingsystem.servicesuit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team.cuc.billingsystem.po.suitservice.SuitPo;
import org.team.cuc.billingsystem.po.userservice.UserPo;
import org.team.cuc.billingsystem.servicesuit.suitMapper.SuitMapper;

import java.util.List;

@Service
public class SuitService {

    @Autowired
    SuitMapper suitMapper;

    public List<SuitPo> listSuit() {
        return suitMapper.selectAllSuits();
    }

    public SuitPo selectById(Integer id) {
        return suitMapper.selectSuitById(id);
    }

    public int insertSuit(SuitPo suitPo){
        return suitMapper.saveOne(suitPo);
    }

}
