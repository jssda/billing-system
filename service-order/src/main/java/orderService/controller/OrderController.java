package orderService.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import orderService.annotation.Log;
import orderService.bean.Order;
import orderService.bean.PaymentOrderDetails;
import orderService.constant.OrderConstant;
import orderService.dto.DataReceiveOrder;
import orderService.dto.OrderPayRequest;
import orderService.dto.OrderRequest;
import orderService.service.OrderService;
import orderService.util.DateUtil;
import orderService.util.LogUtil;
import orderService.util.ModesReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/24 22:55
 */
@RestController
@RequestMapping("/orderService")
@Api(tags= {"订单接口"})
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/dataReceive")
    @Log(name = "订单接收")
    public ModesReturn receiveOrder(@RequestBody DataReceiveOrder dataReceiveOrder) {
        try {
            orderService.receiveOrder(dataReceiveOrder);
        } catch (Exception e) {
            log.error(LogUtil.errorMarker(),"订单接收异常",e);
        }

        ModesReturn modesReturn = new ModesReturn();
        modesReturn.setCode(OrderConstant.SUCCESS);
        modesReturn.setMessage(OrderConstant.SUCCESS_MESSAGE);

        return modesReturn;
    }
    @Log(name = "获取分页数据")
    @PostMapping("/conditionsPage")
    @ApiOperation(value = "根据条件获取订单分页数据",notes = "获取分页数据")
    public ModesReturn conditionsPage(@RequestBody OrderRequest orderRequest, @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        return orderService.conditionsPage(orderRequest, offset, limit);

    }

    @Log(name = "获取待支付订单数量")
    @GetMapping("/getNoPaymentOrder")
    @ApiOperation(value = "获取待支付订单数量",notes = "获取待支付订单数量")
    public ModesReturn getNoPaymentOrder(@RequestParam("userId")String userId) {
        return orderService.getNoPaymentOrder(userId);

    }

    @Log(name = "订单支付")
    @PostMapping("/orderPay")
    @ApiOperation(value = "订单支付",notes = "订单支付")
    public ModesReturn orderPay(@RequestBody OrderPayRequest orderPayRequest) {
        return orderService.orderPay(orderPayRequest);

    }

    @Log(name = "订单支付回调接口")
    @PostMapping("/notify")
    public ModesReturn notify(@RequestBody PaymentOrderDetails paymentOrderDetails) {
        return orderService.orderNotify(paymentOrderDetails);

    }
}

