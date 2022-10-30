package org.team.cuc.billingsystem.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author jssd
 */
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients(basePackages = "org.team.cuc.billingsystem.service")
@SpringBootConfiguration
public class FeignConfig {
}
