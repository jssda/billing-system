package org.team.cuc.billingsystem.eurekaclientdemo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.team.cuc.billingsystem.eurekaclientdemo.service.impl.HelloServiceImpl;

/**
 * 如果想要调用其他服务比如（eureka-client-provider服务)的dc接口，直接使用 @FeignClient 注解
 * fallback为熔断机制，调不通则调用fallback配置的方法
 *
 * @author jssd
 */
@FeignClient(value = "eureka-client-provider", fallback = HelloServiceImpl.class)
public interface HelloService {

    /**
     * demo
     *
     * @return 远程服务调用响应数据或者调用失败熔断响应数据
     */
    @GetMapping(value = "/dc")
    String sayHello();

}
