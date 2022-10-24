package orderService.service;

import orderService.bean.Order;
import orderService.dto.DataReceiveOrder;
import orderService.mapper.OrderMapper;
import orderService.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/24 23:15
 */
@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
   public void receiveOrder(DataReceiveOrder dataReceiveOrder){
       Order order = new Order();
       BeanUtils.copyProperties(dataReceiveOrder,order);
       order.setOrderId(DateUtil.getNewOrderId(dataReceiveOrder.getUserId()));
       order.setOrderType(0);
       order.setOrderStatus(-1);
       order.setCreateTime(DateUtil.DateString2Date(dataReceiveOrder
               .getCreatetime()));
       order.setTotalAmount(order.getTotalPrice());
       orderMapper.insertSelective(order);
    }
}
