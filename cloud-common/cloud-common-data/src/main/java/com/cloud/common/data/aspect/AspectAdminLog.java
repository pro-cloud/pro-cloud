package com.cloud.common.data.aspect;


import com.alibaba.fastjson.JSONObject;
import com.cloud.common.data.annotation.AdminLog;
import com.cloud.common.data.entity.SysLog;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * 系统日志，切面处理类
 * @author Aijm
 * @since 2019/10/9
 */
@Aspect
@AllArgsConstructor
@Slf4j
@Component
public class AspectAdminLog {


	@SneakyThrows
	@Around("@annotation(adminLog)")
	public Object saveSysLog(ProceedingJoinPoint point, AdminLog adminLog) {

		SysLog sysLog = new SysLog();
		if (adminLog != null) {
			// 注解上的描述
			sysLog.setOperation(adminLog.value());
		}
		Object obj = point.proceed();
		log.info("切面日志:{}", JSONObject.toJSONString(sysLog));
		return obj;
	}

}
