package org.team.cuc.billingsystem.bean.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.team.cuc.billingsystem.po.transaction.TransactionRecordPo;

import java.util.Date;

/**
 * @author jssd
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RecordBo extends TransactionRecordPo {

    private Date startTime;

    private Date endTime;

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


