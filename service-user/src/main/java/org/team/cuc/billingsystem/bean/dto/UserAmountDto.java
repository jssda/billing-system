package org.team.cuc.billingsystem.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * 用户金额传输对象
 *
 * @author jssd
 */
@Data
@Validated
public class UserAmountDto {

    @NotNull
    private Integer userId;

    /**
     * 操作类型  0: 加钱，1:减钱
     */
    @NotNull
    @ApiModelProperty("操作类型  0: 加钱，1:减钱")
    private Integer optType;

    /**
     * 用户金额
     */
    @ApiModelProperty("用户余额")
    @NotNull
    private Double amount;

}
