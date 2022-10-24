package alipay.dto;

import alipay.bean.PaymentOrderDetails;
import lombok.Data;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/22 20:57
 */
@Data
public class PaymentOrder extends PaymentOrderDetails {
  private String  returnUrl;

  @Override
  public String toString() {
    return "PaymentOrder{" +
            "returnUrl='" + returnUrl + '\'' +
            super.toString()+
            '}';
  }
}
