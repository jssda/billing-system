package org.team.cuc.billingsystem.po.transaction;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 充值/提现状态表
 *
 * @author root
 */
@Data
public class DepositWithdrawalPo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private Integer id;
    /**
     * 充值/提现单号
     */
    private String code;
    /**
     * 充值/提现时间
     */
    private Date optTime;
    /**
     * 交易金额
     */
    private Double amount;
    /**
     * 到帐时间
     */
    private Date verifyTime;
    /**
     * 充值帐号
     */
    private String account;
    /**
     * 交易方式
     */
    private String type;
    /**
     * 交易状态
     */
    private String status;
    /**
     * 状态
     */
    private String opt;
    /**
     * 时间
     */
    private Date createTime;
}