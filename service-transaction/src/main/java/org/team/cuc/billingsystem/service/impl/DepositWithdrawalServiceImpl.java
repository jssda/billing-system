package org.team.cuc.billingsystem.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.team.cuc.billingsystem.bean.bo.DepositWithdrawalBo;
import org.team.cuc.billingsystem.bean.bo.OrderPayRequestBo;
import org.team.cuc.billingsystem.bean.bo.PaymentOrderBo;
import org.team.cuc.billingsystem.bean.bo.WithdrawBo;
import org.team.cuc.billingsystem.bean.dto.UserAmountDto;
import org.team.cuc.billingsystem.bean.vo.PageVO;
import org.team.cuc.billingsystem.exception.CustomException;
import org.team.cuc.billingsystem.mapper.DepositWithdrawalMapper;
import org.team.cuc.billingsystem.po.transaction.DepositWithdrawalPo;
import org.team.cuc.billingsystem.service.DepositWithdrawalService;
import org.team.cuc.billingsystem.utils.BigDecimalUtil;
import org.team.cuc.billingsystem.utils.StringUtil;

import java.util.List;

/**
 * @author root
 */
@Slf4j
@Service
public class DepositWithdrawalServiceImpl implements DepositWithdrawalService {

    private final DepositWithdrawalMapper depositWithdrawalMapper;

    private final RestTemplate restTemplate;

    public DepositWithdrawalServiceImpl(DepositWithdrawalMapper depositWithdrawalMapper, RestTemplate restTemplate) {
        this.depositWithdrawalMapper = depositWithdrawalMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public DepositWithdrawalPo save(DepositWithdrawalPo depositWithdrawalPo) {
        int id = depositWithdrawalMapper.insertDepositWithdrawal(depositWithdrawalPo);
        depositWithdrawalPo.setId(id);
        return depositWithdrawalPo;
    }

    @Override
    public void withdrawPre(WithdrawBo withdrawBo) {
        DepositWithdrawalPo depositWithdrawalPo = new DepositWithdrawalPo();
        depositWithdrawalPo.setAmount(withdrawBo.getAmount());
        depositWithdrawalPo.setCode(RandomUtil.randomString(12));
        depositWithdrawalPo.setType("支付宝");
        DateTime now = DateUtil.date();
        depositWithdrawalPo.setCreateTime(now);
        depositWithdrawalPo.setStatus("待审核");
        depositWithdrawalPo.setOptTime(now);
        depositWithdrawalPo.setVerifyTime(now);
        save(depositWithdrawalPo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recharge(OrderPayRequestBo orderPayRequestBo) {
        String[] orderIds = orderPayRequestBo.getOrderIds();
        if (orderIds == null || orderIds.length == 0) {
            orderIds = new String[1];
            orderIds[0] = RandomUtil.randomString(12);
        }
        PaymentOrderBo paymentOrder = new PaymentOrderBo();
        paymentOrder.setReturnUrl(orderPayRequestBo.getReturnUrl());
        paymentOrder.setUserId(Integer.parseInt(orderPayRequestBo.getUserId()));
        paymentOrder.setOrderId(orderIds[0]);
        paymentOrder.setPayAmount(BigDecimalUtil.yuan2fen(orderPayRequestBo.getActualAmount()));
        paymentOrder.setNotifyUrl("http://order-server/orderService/notify");
        paymentOrder.setOrderSubject(orderPayRequestBo.getOrderSubject());
        //支付
        String modesReturnStr = restTemplate.postForObject("http://alipay-server/aliPay/pay", paymentOrder, String.class);
        if (StringUtil.isEmpty(modesReturnStr)) {
            rechargeError(orderPayRequestBo, orderIds);
            throw CustomException.userException("支付失败");
        }
        log.info("支付结果：{}", modesReturnStr);

        // 支付成功，加钱  添加流水  添加充值表信息
        UserAmountDto userAmountDto = new UserAmountDto();
        userAmountDto.setUserId(Integer.parseInt(orderPayRequestBo.getUserId()));
        userAmountDto.setAmount(Double.valueOf(orderPayRequestBo.getActualAmount()));
        userAmountDto.setOptType(3);
        Object response2 = restTemplate.postForObject("http://service-user/users/updateAmount", userAmountDto, Object.class);
        if (response2 == null) {
            rechargeError(orderPayRequestBo, orderIds);

            throw CustomException.userException("用户额度添加失败");
        }

        // 添加充值表信息
        DepositWithdrawalPo depositWithdrawalPo = new DepositWithdrawalPo();
        depositWithdrawalPo.setAmount(Double.valueOf(orderPayRequestBo.getActualAmount()));
        depositWithdrawalPo.setCode(orderIds[0]);
        depositWithdrawalPo.setType("支付宝");
        DateTime now = DateUtil.date();
        depositWithdrawalPo.setCreateTime(now);
        depositWithdrawalPo.setStatus("充值成功");
        depositWithdrawalPo.setOptTime(now);
        depositWithdrawalPo.setVerifyTime(now);
        depositWithdrawalPo.setOpt("充值成功");
        save(depositWithdrawalPo);
    }

    /**
     * 充值失败
     */
    private void rechargeError(OrderPayRequestBo orderPayRequestBo, String[] orderIds) {
        // 添加充值表信息
        DepositWithdrawalPo depositWithdrawalPo = new DepositWithdrawalPo();
        depositWithdrawalPo.setAmount(Double.valueOf(orderPayRequestBo.getActualAmount()));
        depositWithdrawalPo.setCode(orderIds[0]);
        depositWithdrawalPo.setType("支付宝");
        DateTime now = DateUtil.date();
        depositWithdrawalPo.setCreateTime(now);
        depositWithdrawalPo.setStatus("充值失败");
        depositWithdrawalPo.setOptTime(now);
        depositWithdrawalPo.setOpt("充值成功");
        save(depositWithdrawalPo);
    }

    @Override
    public PageVO<DepositWithdrawalPo> find(DepositWithdrawalBo depositWithdrawalBo) {
        List<DepositWithdrawalPo> list = depositWithdrawalMapper.selectByConditionPage(depositWithdrawalBo);
        int count = depositWithdrawalMapper.count(depositWithdrawalBo);
        return new PageVO<>(depositWithdrawalBo.getPageSize(), depositWithdrawalBo.getPageNum(), count, list);
    }
}
