package com.cloud.admin;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.nacos.client.naming.utils.IoUtils;
import com.cloud.admin.beans.po.SysUser;
import com.cloud.admin.beans.po.SysUserRole;
import com.cloud.admin.mapper.SysUserMapper;
import com.cloud.admin.mapper.SysUserRoleMapper;
import com.cloud.common.util.util.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class MyAbc {




    @Test
    public void addUser() {
        String[] vds = StrUtils.split("vd", StrUtils.COMMA);
        String[] vdsv = StrUtils.split("vd", StrUtils.COMMA);
    }


}
