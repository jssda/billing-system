package org.team.cuc.billingsystem.servicesuit.bean.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.team.cuc.billingsystem.po.suitservice.ToolPo;
import org.team.cuc.billingsystem.po.transaction.DepositWithdrawalPo;

import java.util.Date;

/**
 * @Description: toolbo
 * @Author: czq
 * @CreateTime: 2022-10-26 17:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ToolBo extends ToolPo {
    /**
     * 每页条数
     */
    private Integer pageSize;

    /**
     * 页数
     */
    private Integer pageNum;


    /**
     * 查询用的偏移量
     */
    private Integer offset;

    public Integer getOffset() {
        return ((pageNum == null || pageNum < 1 ? 1 : pageNum) - 1) * (pageSize == null ? 3 : pageSize);
    }
}
