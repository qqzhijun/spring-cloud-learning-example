package com.lidong.springboot01;

import com.lidong.springboot01.mapper.UserMapper;
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

}