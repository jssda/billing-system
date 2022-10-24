package alipay.config;

import alipay.constant.AliPayConstant;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/22 10:01
 * 接入的电脑网站支付
 */
@Configuration
public class AliConfig {
    @Bean
    AlipayClient getAlipayClient(){
        return   new DefaultAlipayClient(AliPayConstant.GATE_WAY,AliPayConstant.APP_ID,AliPayConstant.PRIVATE_KEY,"json","utf-8",AliPayConstant.ALI_PUBLIC_KEY,"RSA2");
    }
    @Bean("loadBalancedRestTemplate")
    @LoadBalanced
    RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
}
