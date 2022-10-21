package org.team.cuc.billingsystem.po.userservice;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户表
 * t_user
 *
 * @author root
 */
@Data
public class UserPo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private Integer id;
    /**
     * 主账号UIN
     */
    private Integer parentUin;
    /**
     * 主帐号，用户真实姓名
     */
    private String realName;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     *
     */
    private String avatar;
    /**
     *
     */
    private String email;
    /**
     * 用户类型 1为主账号 2为子账号
     */
    private String type;
    /**
     * 用户唯一标识
     */
    private String userCode;
    /**
     * apaas平台用户id
     */
    private String userId;
    /**
     * 调用方服务简称（上架时候填写的服务简称）
     */
    private String subType;
    /**
     * 集团
     */
    private String company;
    /**
     * 是否是管理员
     */
    private Integer admin;
    /**
     * 用户余额
     */
    private Double balance;

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
        UserPo other = (UserPo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getParentUin() == null ? other.getParentUin() == null : this.getParentUin().equals(other.getParentUin()))
                && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
                && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
                && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getSubType() == null ? other.getSubType() == null : this.getSubType().equals(other.getSubType()))
                && (this.getCompany() == null ? other.getCompany() == null : this.getCompany().equals(other.getCompany()))
                && (this.getAdmin() == null ? other.getAdmin() == null : this.getAdmin().equals(other.getAdmin()))
                && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParentUin() == null) ? 0 : getParentUin().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getSubType() == null) ? 0 : getSubType().hashCode());
        result = prime * result + ((getCompany() == null) ? 0 : getCompany().hashCode());
        result = prime * result + ((getAdmin() == null) ? 0 : getAdmin().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentUin=").append(parentUin);
        sb.append(", realName=").append(realName);
        sb.append(", userName=").append(userName);
        sb.append(", avatar=").append(avatar);
        sb.append(", email=").append(email);
        sb.append(", type=").append(type);
        sb.append(", userCode=").append(userCode);
        sb.append(", userId=").append(userId);
        sb.append(", subType=").append(subType);
        sb.append(", company=").append(company);
        sb.append(", admin=").append(admin);
        sb.append(", balance=").append(balance);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}