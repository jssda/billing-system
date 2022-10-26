package org.team.cuc.billingsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.team.cuc.billingsystem.bean.bo.DepositWithdrawalBo;
import org.team.cuc.billingsystem.po.transaction.DepositWithdrawalPo;

import java.util.List;

/**
 * @author root
 */
@Mapper
public interface DepositWithdrawalMapper {


    /**
     * 增加一条数据， 返回插入库中的id
     *
     * @param depositWithdrawalPo 数据实体
     * @return 返回增加到库中的id
     */
    int insertDepositWithdrawal(DepositWithdrawalPo depositWithdrawalPo);

    /**
     * 分页条件查询
     *
     * @param depositWithdrawalBo 查询条件
     * @return 返回查询到的列表
     */
    List<DepositWithdrawalPo> selectByConditionPage(DepositWithdrawalBo depositWithdrawalBo);

    /**
     * 条件查询总条数
     *
     * @param depositWithdrawalBo 条件查询信息
     * @return 总条数
     */
    int count(DepositWithdrawalBo depositWithdrawalBo);
}
