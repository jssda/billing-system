package alipay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author root
 */
@SpringBootApplication
@MapperScan({"alipay.mapper"})
public class AliPayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliPayServiceApplication.class, args);
    }

}
