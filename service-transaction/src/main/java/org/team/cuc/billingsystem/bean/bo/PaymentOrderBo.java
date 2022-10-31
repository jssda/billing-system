package org.team.cuc.billingsystem.bean.bo;

import lombok.Data;

/**
 * @author root
 */
@Data
public class PaymentOrderBo extends PaymentOrderDetailsBo {
    private String returnUrl;

    @Override
    public String toString() {
        return "PaymentOrder{" +
                "returnUrl='" + returnUrl + '\'' +
                super.toString() +
                '}';
    }
}
