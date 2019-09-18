package com.lidong.springboot01;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
@SpringBootTest(classes = Springboot01Application.class)
public class MybatisPlusMultiTenancyApplicationTests {
 
 
  @Autowired
  private ApiContext apiContext;
 
  @Autowired
  private UserMapper userMapper;
 
  @Before
  public void before() {
    // 在上下文中设置当前服务商的ID
    apiContext.setCurrentProviderId(2L);
  }
 
  @Test
  public void insert() {
    User user = new User().setName("新来的Tom老师");
    Assert.assertTrue(userMapper.insert(user) > 0);
 
    user = userMapper.selectById(user.getId());
    log.info("#insert user={}", user);
 
    // 检查插入的数据是否自动填充了租户ID
    Assert.assertEquals(apiContext.getCurrentProviderId(), user.getProviderId());
  }
 
  @Test
  public void selectList() {
    userMapper.selectList(null).forEach((e) -> {
      log.info("#selectList, e={}", e);
      // 验证查询的数据是否超出范围
      Assert.assertEquals(apiContext.getCurrentProviderId(), e.getProviderId());
    });
  }
}