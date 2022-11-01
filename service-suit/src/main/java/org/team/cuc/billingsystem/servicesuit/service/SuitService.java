package org.team.cuc.billingsystem.servicesuit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team.cuc.billingsystem.bean.vo.PageVO;
import org.team.cuc.billingsystem.po.suitservice.SuitPo;
import org.team.cuc.billingsystem.po.suitservice.ToolPo;
import org.team.cuc.billingsystem.po.userservice.UserPo;
import org.team.cuc.billingsystem.servicesuit.bean.bo.SuitBo;
import org.team.cuc.billingsystem.servicesuit.bean.bo.ToolBo;
import org.team.cuc.billingsystem.servicesuit.suitMapper.SuitMapper;

import java.util.List;

@Service
public class SuitService {

    @Autowired
    SuitMapper suitMapper;

    public PageVO listSuit(SuitBo bo) {
        List<SuitPo> suitPos = suitMapper.selectSuit(bo);
        int count = suitMapper.count(bo);
        return new PageVO<>(bo.getPageSize(), bo.getPageNum(), count, suitPos);
    }

    public int insertSuit(SuitBo bo) {
        return suitMapper.saveOne(bo);
    }

    public int updateSuit(SuitBo bo) {
        return suitMapper.updateById(bo);
    }

    public SuitPo selectById(Integer id) {
        return suitMapper.selectSuitById(id);
    }

    public PageVO selectById(SuitBo bo) {
        List<SuitPo> suitPos = suitMapper.selectSuit(bo);
        int count = suitMapper.count(bo);
        return new PageVO<>(bo.getPageSize(), bo.getPageNum(), count, suitPos);
    }

    public int insertSuit(SuitPo suitPo) {
        return suitMapper.saveOne(suitPo);
    }

}
