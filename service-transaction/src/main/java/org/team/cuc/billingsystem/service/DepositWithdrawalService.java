package org.team.cuc.billingsystem.service;


import org.team.cuc.billingsystem.bean.bo.DepositWithdrawalBo;
import org.team.cuc.billingsystem.bean.bo.OrderPayRequestBo;
import org.team.cuc.billingsystem.bean.bo.WithdrawBo;
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

    /**
     * 充值接口
     *
     * @param orderPayRequestBo 充值实体
     * @throws org.team.cuc.billingsystem.exception.CustomException 发生异常或者充值失败
     */
    void recharge(OrderPayRequestBo orderPayRequestBo);

    /**
     * 预提现接口
     *
     * @param withdrawBo 提现
     */
    void withdrawPre(WithdrawBo withdrawBo);
}
