package alipay.controller;

import alipay.bean.PaymentOrderDetails;
import alipay.dto.PaymentOrder;
import alipay.dto.TransformOrder;
import alipay.service.AliPayService;
import alipay.util.ModesReturn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/22 9:36
 */
@RestController
@RequestMapping("/aliPay")
@Api(tags= {"支付接口"})
@Slf4j
public class AliPayController {
    @Autowired
    AliPayService aliPayService;

    @PostMapping("/pay")
    @ApiOperation(value = "支付",notes = "支付")
    public ModesReturn pay(@RequestBody PaymentOrder paymentOrder){
        log.info("支付入参"+paymentOrder);
       return aliPayService.pay(paymentOrder);
    }

    @PostMapping("/transform")
    @ApiOperation(value = "转账",notes = "转账")
    public ModesReturn transform(@RequestBody TransformOrder transformOrder){
        log.info("转账入参"+transformOrder);
        return aliPayService.transform(transformOrder);
    }

}
