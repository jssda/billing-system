package org.team.cuc.billingsystem.po.suitservice;

import lombok.Data;
import org.team.cuc.billingsystem.po.userservice.UserPo;

import java.io.Serializable;

/**
 * 用户表
 * t_user
 *
 * @author root
 */
@Data
public class SuitPo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private Integer id;
    /**
     * 套餐名称
     */
    private String name;
    /**
     * 套餐类型
     */
    private Integer suitType;
    /**
     * 用户姓名
     */
    private Integer costType;
    /**
     *
     */
    private Double price;
    /**
     *
     */
    private Integer status;


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SuitPo other = (SuitPo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getCostType() == null ? other.getCostType() == null : this.getCostType().equals(other.getCostType()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getSuitType() == null ? other.getSuitType() == null : this.getSuitType().equals(other.getSuitType()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCostType() == null) ? 0 : getCostType().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getSuitType() == null) ? 0 : getSuitType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", costType=").append(costType);
        sb.append(", suitType=").append(suitType);
        sb.append(", price=").append(price);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}