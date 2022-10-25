package org.team.cuc.billingsystem.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Service;
import org.team.cuc.billingsystem.bean.bo.DepositWithdrawalBo;
import org.team.cuc.billingsystem.bean.vo.PageVO;
import org.team.cuc.billingsystem.mapper.DepositWithdrawalMapper;
import org.team.cuc.billingsystem.po.transaction.DepositWithdrawalPo;
import org.team.cuc.billingsystem.service.DepositWithdrawalService;

import java.util.List;

/**
 * @author root
 */
@Service
public class DepositWithdrawalServiceImpl implements DepositWithdrawalService {

    private final DepositWithdrawalMapper depositWithdrawalMapper;

    public DepositWithdrawalServiceImpl(DepositWithdrawalMapper depositWithdrawalMapper) {
        this.depositWithdrawalMapper = depositWithdrawalMapper;
    }

    @Override
    public DepositWithdrawalPo save(DepositWithdrawalPo depositWithdrawalPo) {
        depositWithdrawalPo.setCode(RandomUtil.randomString(6));
        depositWithdrawalPo.setOptTime(DateUtil.date());
        depositWithdrawalPo.setStatus("待审核");
        depositWithdrawalPo.setCreateTime(DateUtil.date());
        int id = depositWithdrawalMapper.insertDepositWithdrawal(depositWithdrawalPo);
        depositWithdrawalPo.setId(id);
        return depositWithdrawalPo;
    }

    @Override
    public PageVO<DepositWithdrawalPo> find(DepositWithdrawalBo depositWithdrawalBo) {
        List<DepositWithdrawalPo> list = depositWithdrawalMapper.selectByConditionPage(depositWithdrawalBo);
        int count = depositWithdrawalMapper.count(depositWithdrawalBo);
        return new PageVO<>(depositWithdrawalBo.getPageSize(), depositWithdrawalBo.getPageNum(), count, list);
    }
}
