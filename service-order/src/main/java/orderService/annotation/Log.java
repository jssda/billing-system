package orderService.annotation;

import java.lang.annotation.*;

/**
 * Create By xiaohao 2022/10/25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
@Documented
public @interface Log {
    String name() default "";
}
