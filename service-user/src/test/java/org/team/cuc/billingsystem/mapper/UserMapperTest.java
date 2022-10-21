package org.team.cuc.billingsystem.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.team.cuc.billingsystem.ServiceUserApplication;
import org.team.cuc.billingsystem.po.userservice.UserPo;

import javax.annotation.Resource;

/**
 * @author jssd
 */
@Slf4j
@Transactional
@SpringBootTest(classes = ServiceUserApplication.class)
public class UserMapperTest {

    @Resource
    public UserMapper userMapper;

    @Test
    @Rollback
    public void testSaveUser() {
        UserPo userPo = new UserPo();
        userPo.setRealName("test01");
        userPo.setParentUin(123);
        userPo.setUserCode("asdf");
        Integer id = userMapper.saveOne(userPo);
        userPo.setId(id);
        log.debug("userPo = {}", userPo);
        assert id != null;
    }
}