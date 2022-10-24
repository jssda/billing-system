package org.team.cuc.billingsystem.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * 分页VO
 *
 * @param <T> 数据类型
 * @author jssd
 */
@Data
public class PageVO<T> {

    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 总页数
     */
    private Integer pages;
    /**
     * 总条数
     */
    private Integer total;

    /**
     * 当前页的数据
     */
    private List<T> data;

    public PageVO(Integer pageSize, Integer pageNum, Integer total, List<T> data) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.total = total;
        this.data = data;
        if (pageSize == null) {
            pageSize = 1;
        }
        this.pages = total / pageSize + (total % pageSize == 0 ? 0 : 1);
    }

}
