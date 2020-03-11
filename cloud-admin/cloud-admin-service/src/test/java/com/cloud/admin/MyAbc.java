package com.cloud.admin;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.nacos.client.naming.utils.IoUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public class MyAbc {


    public static String getReadFileByNioV3(File file) {

        StringBuffer lastStr = new StringBuffer("");
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            Charset charset = StandardCharsets.UTF_8;
            CharsetDecoder decoder = charset.newDecoder();
            FileChannel inChannel = in.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            CharBuffer charBuffer = CharBuffer.allocate(1024);
            while (inChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                decoder.decode(byteBuffer, charBuffer, false);
                charBuffer.flip();
                while(charBuffer.hasRemaining()){
                    lastStr.append(charBuffer.get());
                }
                byteBuffer.compact();
                charBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException el) {
                }
            }
        }
        return lastStr.toString();
    }

    public static void main(String[] args) throws IOException {

        EvaluationContext context = new StandardEvaluationContext();

        context.setVariable("tom","3454");

        String redis = "redis";
        // 处理EL 表达式
        SpelExpressionParser parser = new SpelExpressionParser();
        String elKey = parser.parseExpression("'abs'").getValue(context).toString();
        System.out.println(elKey);

    }


}
