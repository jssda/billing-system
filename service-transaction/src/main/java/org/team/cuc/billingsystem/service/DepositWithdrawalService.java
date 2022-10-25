package org.team.cuc.billingsystem.service;


import org.team.cuc.billingsystem.bean.bo.DepositWithdrawalBo;
import org.team.cuc.billingsystem.bean.vo.PageVO;
import org.team.cuc.billingsystem.po.transaction.DepositWithdrawalPo;

/**
 * @author root
 */
public interface DepositWithdrawalService {

    /**
     * 增加一条数据
     *
     * @param depositWithdrawalPo 实体信息
     * @return 返回增加的数据信息
     */
    DepositWithdrawalPo save(DepositWithdrawalPo depositWithdrawalPo);

    /**
     * 分页条件查询充值提现信息
     *
     * @param depositWithdrawalBo 条件实体
     * @return 返回查询到的分页信息
     */
    PageVO<DepositWithdrawalPo> find(DepositWithdrawalBo depositWithdrawalBo);
}
