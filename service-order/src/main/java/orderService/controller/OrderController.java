package orderService.controller;

import lombok.extern.slf4j.Slf4j;
import orderService.bean.Order;
import orderService.constant.OrderConstant;
import orderService.dto.DataReceiveOrder;
import orderService.service.OrderService;
import orderService.util.DateUtil;
import orderService.util.ModesReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/24 22:55
 */
@RestController
@RequestMapping("/orderService")
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/dataReceive")
    public ModesReturn  receiveOrder(@RequestBody DataReceiveOrder dataReceiveOrder){
        log.info("接受订单"+dataReceiveOrder);
        try {
            orderService.receiveOrder(dataReceiveOrder);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        ModesReturn modesReturn = new ModesReturn();
        modesReturn.setCode(OrderConstant.SUCCESS);
        modesReturn.setMessage(OrderConstant.SUCCESS_MESSAGE);

        return modesReturn;
    }
}
