package com.cloud.admin;


import com.cloud.admin.mapper.SysUserMapper;
import com.cloud.common.cache.redis.RedisDao;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.MediaType;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CloudAdminApplicationTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private RedisLockRegistry redisLockRegistry;


    @Autowired
    private SysUserMapper sysUserMapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/user").param("username", "jojo").param("age", "18").param("ageTo", "60").param("xxx", "yyy")
                        // .param("size", "15")
                        // .param("page", "3")
                        // .param("sort", "age,desc")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void myfist(){

//        redisDao.hmSet("2222", "4444", "999");
//        Map<String, String> map = Maps.newHashMap();
//        map.put("my","ku");
//        map.put("myv","ku33");
//        redisDao.hmSet("22226", "4444", map);
//
//        Map<Object, Object> objectObjectMap1 = redisDao.hmGet("2222");
//        Map<Object, Object> objectObjectMap = redisDao.hmGet("22226");
//
//        Object o = redisDao.hmGet("2222", "4444");
//        Object bo = redisDao.hmGet("22226", "4444");
//
//        redisDao.lRightPush("66:vmpush", "45667", true);
////        redisDao.lLeftPush("vmpush", "45667");
////        redisDao.lLeftPush("vmpush", "45667678");
////        redisDao.lRightPush("vmpush", "my");
//        Object vmpush = redisDao.lRange("vmpush");
//
//        Object vmpush1 = redisDao.lLeftPop("vmpush");

//        SysUser sysUser = new SysUser();
//        sysUser.setId(1168183478441873408L);
//        sysUser.setName("222222");
//        sysUser.setPassword("");
//        sysUserMapper.updateById(sysUser);
//        log.info("22");


        // 加密
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("password345");
        String newPassword = textEncryptor.encrypt("userId=10000000&groupId=10000000");
        System.out.println(newPassword);
        // 解密
        BasicTextEncryptor textEncryptor2 = new BasicTextEncryptor();
        textEncryptor2.setPassword("password345");
        String oldPassword = textEncryptor2.decrypt(newPassword);
        System.out.println(oldPassword);

        log.info("222");

    }


    @Test
    public void keys() {

        new Thread(new Runnable() {
        @Override
        public void run() {
            Lock mycomm = redisLockRegistry.obtain("mycomm");
            try {
                     System.out.println(Thread.currentThread().getName()+"准备获取锁");
                    boolean b = mycomm.tryLock(0, TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName()+"获取锁"+b);

            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName()+"获取锁异常");

            }
        }}).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Lock mycomm = redisLockRegistry.obtain("mycomm");
                try {

                        System.out.println(Thread.currentThread().getName()+"----准备获取锁");
                        boolean b = mycomm.tryLock(0, TimeUnit.SECONDS);
                        System.out.println(Thread.currentThread().getName()+"----获取锁"+b);


                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName()+"----获取锁异常");
                }
            }}).start();


        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void mydad(){

        EvaluationContext context = new StandardEvaluationContext();

            context.setVariable("tom","3454");

        // 处理EL 表达式
        SpelExpressionParser parser = new SpelExpressionParser();
        String elKey = parser.parseExpression("234").getValue(context).toString();
        log.info(elKey);
    }

    @Test
    public void keysTest() {


        Lock mycomm = redisLockRegistry.obtain("mycomm");
        mycomm.lock();

        try {
            Thread.sleep(10000);
            Lock adf = redisLockRegistry.obtain("mycommvv");
            adf.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
