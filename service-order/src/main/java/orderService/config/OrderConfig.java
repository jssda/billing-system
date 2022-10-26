package orderService.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/26 21:04
 */
@Configuration
public class OrderConfig {
    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
}
