package orderService.dto;

import lombok.Data;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/26 20:55
 */
@Data
public class OrderPayRequest {
    public String userId;
    public String[] orderIds;
    public String actualAmount;
    public String returnUrl;
    public String orderSubject;
}
