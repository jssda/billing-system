package orderService.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import orderService.bean.Order;
import orderService.bean.OrderExample;
import orderService.bean.PaymentOrder;
import orderService.bean.PaymentOrderDetails;
import orderService.constant.OrderConstant;
import orderService.dto.*;
import orderService.mapper.OrderMapper;
import orderService.util.BigDecimalUtil;
import orderService.util.DateUtil;
import orderService.util.LogUtil;
import orderService.util.ModesReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Autowired
    RestTemplate restTemplate;

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


    public ModesReturn getNoPaymentOrder(String userId) {
        ModesReturn modesReturn = new ModesReturn();
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andOrderStatusEqualTo(-1);
        int i = orderMapper.countByExample(orderExample);
        modesReturn.setData(i);
        modesReturn.setMessage(OrderConstant.SUCCESS_MESSAGE);
        modesReturn.setCode(OrderConstant.SUCCESS);
        return modesReturn;
    }

    public ModesReturn conditionsPage(@RequestBody OrderRequest orderRequest, @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        ModesReturn modesReturn = new ModesReturn();

        try {
            OrderExample orderExample = getOrderExample(orderRequest);

            List<Order> orders = orderMapper.selectPageByExample(orderExample, offset, limit);
            int count = orderMapper.countByExample(orderExample);
            Map<String, Object> map = new HashMap<>();
            map.put("total", count);
            map.put("data", orders);
            log.debug(LogUtil.marker(count), "总数据条数");
            modesReturn.setData(map);
            modesReturn.setMessage(OrderConstant.SUCCESS_MESSAGE);
            modesReturn.setCode(OrderConstant.SUCCESS);
        } catch (Exception e) {
            log.error(LogUtil.errorMarker(), "分页数据查询异常", e);
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

    public ModesReturn orderPay(OrderPayRequest orderPayRequest) {
        //检查余额是否充足
        if (checkUserAccount(orderPayRequest)) {
            ModesReturn modesReturn = new ModesReturn();
            modesReturn.setData("");
            modesReturn.setCode(OrderConstant.SUCCESS);
            modesReturn.setMessage(OrderConstant.SUCCESS_MESSAGE);
            return modesReturn;
        }
        List<Order> orderList = new ArrayList<>();

        String payOrderId = DateUtil.getNewOrderId(orderPayRequest.getUserId());
        //获取所有待支付订单
        for (String orderId : orderPayRequest.getOrderIds()) {
            OrderExample orderExample = new OrderExample();
            OrderExample.Criteria criteria = orderExample.createCriteria();
            criteria.andUserIdEqualTo(orderPayRequest.getUserId());
            criteria.andOrderIdEqualTo(orderId);
            List<Order> list = orderMapper.selectByExample(orderExample);
            if (list != null && list.size() > 0) {
                Order order = list.get(0);
                order.setPayOrderId(payOrderId);
                order.setOrderStatus(1);
                orderList.add(order);
            }
        }
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setReturnUrl(orderPayRequest.getReturnUrl());
        paymentOrder.setUserId(Integer.parseInt(orderPayRequest.getUserId()));
        paymentOrder.setOrderId(payOrderId);
        paymentOrder.setPayAmount(BigDecimalUtil.yuan2fen(orderPayRequest.getActualAmount()));
        paymentOrder.setNotifyUrl("http://order-server/orderService/notify");
        paymentOrder.setOrderSubject(orderPayRequest.getOrderSubject());
        //更新支付订单号以及支付状态
        for (Order order : orderList) {
            orderMapper.updateByPrimaryKeySelective(order);
        }
        //支付
        return restTemplate.postForObject("http://alipay-server/aliPay/pay", paymentOrder, ModesReturn.class);


    }

    /**
     * 检查用户余额是否足以抵扣此次订单支付
     *
     * @param orderPayRequest
     * @return
     */
    private boolean checkUserAccount(OrderPayRequest orderPayRequest) {
        try {
            String userId = orderPayRequest.getUserId();
            String actualAmount = orderPayRequest.getActualAmount();
            log.debug(LogUtil.marker("http://service-user/users/id/"+orderPayRequest.getUserId()), "获取的用户信息请求地址");
            Object userObj = restTemplate.getForObject("http://service-user/users/id/" + orderPayRequest.getUserId(), Object.class);
            log.debug(LogUtil.marker(userObj), "获取的用户信息");
            JSONObject object = JSON.parseObject(JSON.toJSONString(userObj));
            Object data = object.get("data");
            UserPo user = JSON.parseObject(JSON.toJSONString(data), UserPo.class);
            log.debug(LogUtil.marker(user), "用户信息");
            if (user != null) {
                log.debug(LogUtil.marker(user.getBalance() + ""), "用户余额");
                log.debug(LogUtil.marker(actualAmount), "订单金额");
                int compare = BigDecimalUtil.compare(user.getBalance() + "", actualAmount);
                if (compare >= 0) {
                    UserAmountDto userAmountDto = new UserAmountDto();
                    userAmountDto.setUserId(Integer.parseInt(userId));
                    userAmountDto.setAmount(new BigDecimal(actualAmount).doubleValue());
                    userAmountDto.setOptType(1);
                    log.debug(LogUtil.marker(userAmountDto), "扣减余额入参");
                    Object response = restTemplate.postForObject("http://service-user/users/updateAmount", userAmountDto, Object.class);
                    log.debug(LogUtil.marker(response), "扣减余额响应");
                    return true;
                }

            }

        }catch (Exception e){
            log.debug(LogUtil.errorMarker(),"查询用户余额异常",e);
        }
        return false;


    }

    /**
     * 支付通知
     *
     * @param paymentOrderDetails 通知入参
     * @return
     */
    public ModesReturn orderNotify(PaymentOrderDetails paymentOrderDetails) {
        String orderId = paymentOrderDetails.getOrderId();
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andPayOrderIdEqualTo(orderId);
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        for (Order order : orderList) {
            order.setOrderStatus(paymentOrderDetails.getOrderStatus());
            order.setPaymentOrderId(paymentOrderDetails.getPaymentId());
            order.setActualAmount(order.getTotalAmount());
            orderMapper.updateByPrimaryKeySelective(order);
        }
        //分账
        ledger(orderList);
        ModesReturn modesReturn = new ModesReturn();
        modesReturn.setMessage(OrderConstant.SUCCESS_MESSAGE);
        modesReturn.setCode(OrderConstant.SUCCESS);
        return modesReturn;
    }

    /**
     * 分账
     *
     * @param orderList 支付成功的订单
     */
    private void ledger(List<Order> orderList) {
        log.debug(LogUtil.marker(orderList), "开始分账");

    }
}
