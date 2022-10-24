package orderService.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author xiaohao
 * @version 1.0
 * @date 2022/10/24 23:04
 * SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS")
 */
public class DateUtil {

  public static Date DateString2Date(String date){
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date parse = null;
      try {
          parse = simpleDateFormat.parse(date);

      } catch (ParseException e) {
          e.printStackTrace();
      }
      return parse;
  }

    public static String getNewOrderId(String userId){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = "";
        try {
             format = simpleDateFormat.format(new Date());
            format=format+userId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return format;
    }
}
