package orderService.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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

    private Date createTime;
}