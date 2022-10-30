package org.team.cuc.billingsystem.utils;


import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @Description:
 * @Author: czq
 * @CreateTime: 2022-10-28 17:18
 */
public class StringUtil {

    public static boolean isEmpty(String string) {
        return (string == null || string.trim().length() == 0);
    }


    public static<T> List<T> getListFromString(String string, String sign, Function<String, ? extends T> function) {
        List<T> resultList = new ArrayList<T>();
        if (StringUtil.isEmpty(string)) {
            return resultList;
        }
        List<String> ss = Splitter.on(sign).omitEmptyStrings().trimResults().splitToList(string);
        for (String idS : ss) {
            resultList.add(function.apply(idS));
        }
        return resultList;
    }
}
