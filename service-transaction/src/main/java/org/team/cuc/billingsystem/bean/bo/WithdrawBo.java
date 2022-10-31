package org.team.cuc.billingsystem.bean.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jssd
 */
@Data
@ApiModel("提现接口")
public class WithdrawBo {

    private Integer userId;

    @ApiModelProperty("提现金额")
    private Double amount;

}
