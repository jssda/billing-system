package alipay.config;

import alipay.constant.AliPayConstant;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
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
    AlipayClient getAlipayClient() throws AlipayApiException {
        CertAlipayRequest alipayConfig = new CertAlipayRequest();
        alipayConfig.setPrivateKey(AliPayConstant.PRIVATE_KEY);
        alipayConfig.setServerUrl(AliPayConstant.GATE_WAY);
        alipayConfig.setAppId(AliPayConstant.APP_ID);
        alipayConfig.setCharset("utf-8");
        alipayConfig.setSignType("RSA2");
        alipayConfig.setEncryptor("");
        alipayConfig.setFormat("json");
        alipayConfig.setCertPath("/opt/appPublicCert.crt");
        alipayConfig.setAlipayPublicCertPath("/opt/alipayPublicCert.crt");
        alipayConfig.setRootCertPath("/opt/alipayRootCert.crt");
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        return  alipayClient;
    }
    @Bean("loadBalancedRestTemplate")
    @LoadBalanced
    RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
}
