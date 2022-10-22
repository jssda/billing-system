package org.team.cuc.billingsystem.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.team.cuc.billingsystem.mapper.UserMapper;
import org.team.cuc.billingsystem.po.userservice.UserPo;
import org.team.cuc.billingsystem.service.UserService;
import org.team.cuc.billingsystem.utils.JsonUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author root
 * 针对表【t_user(用户表)】的数据库操作Service实现
 * 2022-10-21 20:26:06
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RestTemplate restTemplate;

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserPo> listUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public UserPo getUserInfo(String token) {
        String accessId = "accessId";
        String accessKey = "accessKey";
        double random = Math.random();
        String signatureNonce = random + "";
        String timeStamp = DateUtil.date().toTimestamp().toString();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("subToken", token);
        objectNode.put("subType", "nces");
        String body = JsonUtil.objectToJson(objectNode);

        String stringToSign = accessId + accessKey + signatureNonce + timeStamp + body;
        String signature = SecureUtil.md5(stringToSign);

        // 请求头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-AccessId", accessId);
        headers.add("X-Signature", signature);
        headers.add("X-TimeStamp", timeStamp);
        headers.add("X-SignatureNonce", timeStamp);
        headers.setContentType(MediaType.valueOf("application/x-www-form-urlencoded"));

        // 组装请求信息
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange("http://apaas-api.apaas.cuc.edu.cn/console/account/checkToolAccess", HttpMethod.POST, httpEntity, String.class);
        log.info(String.valueOf(response));

        String userCode = "";
        UserPo userPo = userMapper.selectUserByUserCode(userCode);
        if (Objects.isNull(userPo)) {
            // 如果当前用户不存在， 则创建用户
            userPo = new UserPo();
            Integer userId = userMapper.saveOne(userPo);
            userPo.setId(userId);
        }

        return userPo;
    }

    @Override
    public UserPo getUserById(Integer id) {
        return userMapper.selectUserById(id);
    }
}




