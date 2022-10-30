package orderService.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户金额传输对象
 *
 * @author jssd
 */
@Data
public class UserAmountDto {

    private Integer userId;

    /**
     * 用户金额
     */
    @ApiModelProperty("用户余额")
    private Double amount;

    private Integer optType;

}
