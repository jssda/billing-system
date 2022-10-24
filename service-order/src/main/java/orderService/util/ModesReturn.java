package orderService.util;

import lombok.Data;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/22 21:01
 */
@Data
public class ModesReturn {
    private String code;
    private String message;
    private Object data;
}
