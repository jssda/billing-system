package org.team.cuc.billingsystem.po.transaction;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author root
 */
@Data
public class TransactionRecordPo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String code;
    private String type;
    private String channel;
    private Double amount;
    private Date createTime;
}