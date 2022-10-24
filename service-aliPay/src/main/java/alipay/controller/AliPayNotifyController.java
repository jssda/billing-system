package alipay.controller;

import alipay.service.AliPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/22 11:45
 */
@Controller
@RequestMapping("/aliPay")
@Slf4j
public class AliPayNotifyController {
    @Autowired
    AliPayService aliPayService;

    @PostMapping("/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer;
        try {

            aliPayService.notify(request);
            writer = response.getWriter();
            writer.println("success");
        } catch (Exception e) {
            log.error("通知异常",e);

        }

    }

}
