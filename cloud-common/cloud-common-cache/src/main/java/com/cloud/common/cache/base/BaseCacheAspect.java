package com.cloud.common.cache.base;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.cloud.common.cache.annotation.CacheConf;
import com.cloud.common.cache.constants.CacheKeys;
import com.cloud.common.cache.constants.CacheScope;
import com.cloud.common.cache.redis.RedisDao;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @Author Aijm
 * @Description cache 缓存切面
 * @Date 2019/8/30
 */
public class BaseCacheAspect {



    /**
     *
     * 获取key CacheConf 的优先级小于 Cache
     * @param point
     * @param cache
     * @param key
     * @return
     */
    public String getKey(ProceedingJoinPoint point,
                                CacheScope cache, String key) {
        // 处理key
        MethodSignature signature = (MethodSignature) point.getSignature();
        StringBuilder sbr = new StringBuilder();
        CacheConf conf = AnnotationUtils.findAnnotation(signature.getDeclaringType(), CacheConf.class);
        // 类上的CacheScope 小于方法上的CacheScope
        if (cache.equals(CacheScope.APPLICATION)) {
            if (conf != null) {
                sbr.append(conf.scope().getCacheName()) .append(":");
            } else {
                sbr.append(cache.getCacheName()).append(":");
            }
        } else {
            sbr.append(cache.getCacheName()).append(":");
        }

        if (StrUtil.isBlank(key)) {
            Method method = signature.getMethod();
            Object[] params = point.getArgs();
            sbr.append(signature.getDeclaringTypeName())
                    .append(method.getName());
            for (Object param : params) {
                sbr.append(JSON.toJSONString(param).hashCode());
            }

        } else {
            String[] paras= signature.getParameterNames();
            getElKey(point, key, sbr, paras);
        }

        return sbr.toString();
    }


    /**
     * 将结果放入redis 缓存中
     * @param redisDao
     * @param result
     * @param key
     * @param expireTime
     */
    public void putCache(RedisDao redisDao, Object result,
                                String key, long expireTime) {
        if (result == null) {
            redisDao.vSet(key, CacheKeys.EMPTY_OBJ, CacheKeys.EXPIRETIME_NULL);
        } else {
            if (expireTime== 0) {
                redisDao.vSet(key, result);
            } else {
                redisDao.vSet(key, result, expireTime);
            }
        }
    }


    /**
     * 通过el表达式获取到key
     * @param point
     * @param key
     * @param sbr 返回结果
     * @param paras
     */
    public void getElKey(ProceedingJoinPoint point, String key, StringBuilder sbr, String[] paras) {
        if (ArrayUtil.isNotEmpty(paras)) {
            EvaluationContext context = new StandardEvaluationContext();
            for (int i = 0, length = paras.length; i < length; i++) {
                Object[] params = point.getArgs();
                context.setVariable(paras[i], params[i]);
            }
            // 处理EL 表达式
            SpelExpressionParser parser = new SpelExpressionParser();
            String elKey = parser.parseExpression(key).getValue(context).toString();
            sbr.append(elKey);
        } else {
            sbr.append(key);
        }
    }
}
