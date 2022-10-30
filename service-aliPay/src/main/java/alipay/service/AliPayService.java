package alipay.service;

import alipay.bean.PaymentOrderDetails;
import alipay.bean.PaymentOrderDetailsExample;
import alipay.constant.AliPayConstant;
import alipay.dto.PaymentOrder;
import alipay.dto.TransformOrder;
import alipay.mapper.PaymentOrderDetailsMapper;
import alipay.mapper.TransformOrderDetailsMapper;
import alipay.util.BigDecimalUtil;
import alipay.util.ModesReturn;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.domain.AlipayFundTransUniTransferModel;
import com.alipay.api.domain.Participant;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/22 9:36
 */
@Service
@Slf4j
public class AliPayService {
    @Autowired
    AlipayClient alipayClient;
    @Autowired
    PaymentOrderDetailsMapper paymentOrderDetailsMapper;
    @Autowired
    TransformOrderDetailsMapper transformOrderDetailsMapper;

    @Autowired
    RestTemplate loadBalancedRestTemplate;


    /**
     * 异步通知
     *
     * @param request
     */
    public void notify(HttpServletRequest request) {
        String orderId = request.getParameter("out_trade_no");
        String paymentId = request.getParameter("trade_no");
        String tradeStatus = request.getParameter("trade_status");
        String actualAmount = request.getParameter("buyer_pay_amount");
        PaymentOrderDetailsExample paymentOrderDetailsExample = new PaymentOrderDetailsExample();
        PaymentOrderDetailsExample.Criteria criteria = paymentOrderDetailsExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<PaymentOrderDetails> paymentOrderDetailsList = paymentOrderDetailsMapper.selectByExample(paymentOrderDetailsExample);
        if (paymentOrderDetailsList != null && paymentOrderDetailsList.size() == 1) {
            PaymentOrderDetails paymentOrderDetails = paymentOrderDetailsList.get(0);
            paymentOrderDetails.setActualAmount(BigDecimalUtil.yuan2fen(actualAmount));
            paymentOrderDetails.setPaymentId(paymentId);
            int orderStatus = "TRADE_SUCCESS".equals(tradeStatus) ? 2 : 3;
            paymentOrderDetails.setOrderStatus(orderStatus);
            //交易状态修改
            updateOrder(paymentOrderDetails);
            //通知内部服务
            notifyInternalService(paymentOrderDetails);
        }


    }

    private void notifyInternalService(PaymentOrderDetails paymentOrderDetails) {
        String notifyUrl = paymentOrderDetails.getNotifyUrl();
        log.info("通知内部服务参数:" + paymentOrderDetails.toString());
        Object response = loadBalancedRestTemplate.postForObject(notifyUrl, paymentOrderDetails, Object.class);
        log.info("通知内部服务响应:" + response.toString());


    }

    /**
     * 更新订单
     */
    private void updateOrder(PaymentOrderDetails paymentOrderDetails) {
        paymentOrderDetails.setUpdateTime(new Date());
        paymentOrderDetailsMapper.updateByPrimaryKeySelective(paymentOrderDetails);
    }

    /**
     * 支付
     *
     * @param paymentOrder paymentOrder
     * @return
     */
    public ModesReturn pay(PaymentOrder paymentOrder) {
        ModesReturn modesReturn = new ModesReturn();

        try {
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            request.setNotifyUrl("http://123.57.39.124:8900/aliPay/notify");
            request.setReturnUrl(paymentOrder.getReturnUrl());
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", paymentOrder.getOrderId());
            bizContent.put("total_amount", BigDecimalUtil.fen2yuan(paymentOrder.getPayAmount()));
            bizContent.put("subject", paymentOrder.getOrderSubject());
            bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
            request.setBizContent(bizContent.toString());
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                log.info("调用成功");
                modesReturn.setCode(AliPayConstant.SUCCESS);
                modesReturn.setMessage(AliPayConstant.SUCCESS_MESSAGE);
                modesReturn.setData(response.getBody());
                System.out.println(response.getBody());
                //落库
                saveOrder(paymentOrder);

            } else {
                log.info("调用失败");
                modesReturn.setCode(AliPayConstant.ERROR);
                modesReturn.setMessage(AliPayConstant.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            log.error("支付异常", e);
            modesReturn.setCode(AliPayConstant.ERROR);
            modesReturn.setMessage(AliPayConstant.ERROR_MESSAGE);
        }
        return modesReturn;

    }

    /**
     * 交易落库
     *
     * @param paymentOrder paymentOrder
     */
    private void saveOrder(PaymentOrder paymentOrder) {
        paymentOrder.setOrderStatus(-1);
        Date date = new Date();
        paymentOrder.setCreateTime(date);
        paymentOrder.setUpdateTime(date);
        paymentOrderDetailsMapper.insertSelective(paymentOrder);
    }

    /**
     * 转账
     *
     * @param transformOrder
     * @return
     */
    public ModesReturn transform(TransformOrder transformOrder) {
        ModesReturn modesReturn = new ModesReturn();
        try {
            AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
            AlipayFundTransUniTransferModel model = new AlipayFundTransUniTransferModel();
            model.setOutBizNo(transformOrder.getOrderId());
            model.setRemark(transformOrder.getOrderSubject());
            model.setBusinessParams("{\"payer_show_name_use_alias\":\"true\"}");
            model.setBizScene("DIRECT_TRANSFER");
            Participant payeeInfo = new Participant();
            payeeInfo.setIdentity(transformOrder.getAliId());
            payeeInfo.setIdentityType("ALIPAY_LOGON_ID");
            payeeInfo.setName(transformOrder.getAliName());
            model.setPayeeInfo(payeeInfo);
            model.setTransAmount(BigDecimalUtil.fen2yuan(transformOrder.getTransformAmount()));
            model.setProductCode("TRANS_ACCOUNT_NO_PWD");
            model.setOrderTitle(transformOrder.getOrderSubject());
            request.setBizModel(model);
            AlipayFundTransUniTransferResponse response = alipayClient.certificateExecute(request);
            String responseBody = response.getBody();
            JSONObject jsonObject = JSONObject.parseObject(responseBody);
            Object alipay_fund_trans_uni_transfer_response = jsonObject.get("alipay_fund_trans_uni_transfer_response");
            JSONObject data = JSONObject.parseObject(JSON.toJSONString(alipay_fund_trans_uni_transfer_response));
            log.info("转账响应结果" + jsonObject);
            if (response.isSuccess()) {
                log.info("调用成功");
                transformOrder.setOrderStatus(2);
                transformOrder.setUpdateTime(new Date());
                transformOrder.setCreateTime(new Date());
                transformOrder.setPaymentId((String) data.get("order_id"));
                transformOrderDetailsMapper.insertSelective(transformOrder);
                modesReturn.setData(transformOrder);
                modesReturn.setCode(AliPayConstant.SUCCESS);
                modesReturn.setMessage(AliPayConstant.SUCCESS_MESSAGE);
            } else {
                log.info("调用失败");
                modesReturn.setCode(AliPayConstant.ERROR);
                modesReturn.setMessage(AliPayConstant.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            modesReturn.setCode(AliPayConstant.ERROR);
            modesReturn.setMessage(AliPayConstant.ERROR_MESSAGE);
        }

        return modesReturn;

    }
}
