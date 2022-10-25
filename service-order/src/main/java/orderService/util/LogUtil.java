package orderService.util;

import net.logstash.logback.marker.LogstashMarker;
import net.logstash.logback.marker.ObjectAppendingMarker;

/**
 * Create By xiaohao 2022/10/25
 */
public class LogUtil {
    public static LogstashMarker marker(Object o) {
        ObjectAppendingMarker data = new ObjectAppendingMarker("data", o);
        data.and(new ObjectAppendingMarker("log_pos", "处理中"));
        return data;
    }

    public static LogstashMarker startMarker(Object o) {
        ObjectAppendingMarker data = new ObjectAppendingMarker("data", o);
        data.and(new ObjectAppendingMarker("log_pos", "开始处理"));
        return data;
    }

    public static LogstashMarker endMarker(Object o) {
        ObjectAppendingMarker data = new ObjectAppendingMarker("data", o);
        data.and(new ObjectAppendingMarker("log_pos", "结束处理"));
        return data;
    }

    public static LogstashMarker errorMarker() {
        return new ObjectAppendingMarker("log_pos", "异常");
    }
}
