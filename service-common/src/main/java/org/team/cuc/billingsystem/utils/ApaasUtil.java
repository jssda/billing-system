package org.team.cuc.billingsystem.utils;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.team.cuc.billingsystem.po.suitservice.ToolPo;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Description: 访问Apaas
 * @Author: czq
 * @CreateTime: 2022-10-26 15:08
 */
public class ApaasUtil {
    public static final String ACCESS_ID = "645740746176";
    public static final String ACCESS_KEY = "25mKPb50fzKjGdb0IiVAj9e5DGC368s0";

    public static final String TOOL_LIST_URL = "http://apaas-api.apaas.cuc.edu.cn/toolOpen/tool/getAllToolList";

    public static <T extends Object> ResponseEntity send(String url, String body, Class<T> convert) {
        long now = System.currentTimeMillis() / 1000;
        int sha1PRNG = 0;
        try {
            sha1PRNG = SecureRandom.getInstance("SHA1PRNG").nextInt(Integer.MAX_VALUE - 100000) + 100000;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String signStr = ACCESS_ID + "\n" +
                ACCESS_KEY + "\n" +
                sha1PRNG + "\n" +
                now + "\n" +
                body;

        String md5 = SecureUtil.md5(signStr);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-AccessId", "645740746176");
        headers.add("X-Signature", md5);
        headers.add("X-SignatureNonce", sha1PRNG + "");
        headers.add("X-TimeStamp", now + "");
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        return HttpUtil.post(url, convert, body, headers);
    }


}
