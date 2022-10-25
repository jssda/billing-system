package orderService.service;

import lombok.extern.slf4j.Slf4j;
import orderService.bean.Order;
import orderService.bean.OrderExample;
import orderService.constant.OrderConstant;
import orderService.dto.DataReceiveOrder;
import orderService.dto.OrderRequest;
import orderService.mapper.OrderMapper;
import orderService.util.DateUtil;
import orderService.util.LogUtil;
import orderService.util.ModesReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/24 23:15
 */
@Service
@Slf4j
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public void receiveOrder(DataReceiveOrder dataReceiveOrder) {
        Order order = new Order();
        BeanUtils.copyProperties(dataReceiveOrder, order);
        order.setOrderId(DateUtil.getNewOrderId(dataReceiveOrder.getUserId()));
        order.setOrderType(0);
        order.setOrderStatus(-1);
        order.setCreateTime(DateUtil.DateString2Date(dataReceiveOrder
                .getCreatetime()));
        order.setTotalAmount(order.getTotalPrice());
        orderMapper.insertSelective(order);
    }

    public ModesReturn conditionsPage(@RequestBody OrderRequest orderRequest, @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        ModesReturn modesReturn = new ModesReturn();

        try {
            OrderExample orderExample = getOrderExample(orderRequest);

            List<Order> orders = orderMapper.selectPageByExample(orderExample,offset,limit);
            int count =orderMapper.countByExample(orderExample);
            Map<String,Object> map = new HashMap<>();
            map.put("total",count);
            map.put("data",orders);
            log.debug(LogUtil.marker(count),"总数据条数");
            modesReturn.setData(map);
            modesReturn.setMessage(OrderConstant.SUCCESS_MESSAGE);
            modesReturn.setCode(OrderConstant.SUCCESS);
        } catch (Exception e) {
            log.error(LogUtil.errorMarker(),"分页数据查询异常",e);
            modesReturn.setMessage(OrderConstant.ERROR_MESSAGE);
            modesReturn.setCode(OrderConstant.ERROR);
        }
        return modesReturn;
    }

    private OrderExample getOrderExample(OrderRequest orderRequest) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if (orderRequest.getOrderId() != null) {
            criteria.andOrderIdEqualTo(orderRequest.getOrderId());
        }
        if (orderRequest.getOrderStatus() != null) {
            criteria.andOrderStatusEqualTo(orderRequest.getOrderStatus());
        }
        if (orderRequest.getUserId() != null) {
            criteria.andUserIdEqualTo(orderRequest.getUserId());
        }
        String[] createTimes = orderRequest.getCreateTimes();
        if (createTimes != null) {
            if (createTimes.length > 0) {
                criteria.andCreateTimeGreaterThanOrEqualTo(DateUtil.DateString2Date(createTimes[0]));
            }
            if (createTimes.length > 1) {
                criteria.andCreateTimeLessThanOrEqualTo(DateUtil.DateString2Date(createTimes[1]));
            }
        }
        return orderExample;
    }
}
