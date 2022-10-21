package org.team.cuc.billingsystem.eurekaclientdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team.cuc.billingsystem.eurekaclientdemo.service.HelloService;

/**
 * @author jssd
 */
@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/sayHello")
    public String sayHello() {
        return helloService.sayHello();
    }

}
