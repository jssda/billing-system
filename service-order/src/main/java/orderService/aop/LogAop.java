package orderService.aop;


import lombok.extern.slf4j.Slf4j;
import orderService.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

/**
 * Create By xiaohao 2022/10/25
 */
@Aspect
@Component
@Slf4j
public class LogAop {
    @Pointcut("@annotation(orderService.annotation.Log)")
    public void logPrint() {
    }

    @Around("logPrint() && @annotation(Log)")
    public Object doAround( ProceedingJoinPoint proceedingJoinPoint,orderService.annotation.Log Log) throws Throwable {
        try {
            MDC.put("x_global_session_id", UUID.randomUUID().toString());
            MDC.put("request_type", Log.name());
            log.debug(LogUtil.startMarker(Arrays.toString(proceedingJoinPoint.getArgs())),"前端请求入参");
            Object proceed = proceedingJoinPoint.proceed();
            log.debug(LogUtil.endMarker(proceed),"前端请求响应");
            return proceed;
        }finally {
            MDC.clear();
        }

    }

}
