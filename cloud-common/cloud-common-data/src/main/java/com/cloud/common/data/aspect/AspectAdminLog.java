package com.cloud.common.data.aspect;


import cn.hutool.json.JSONUtil;
import com.cloud.common.data.annotation.AdminLog;
import com.cloud.common.data.entity.SysLog;
import com.cloud.common.data.user.SystemService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


/**
 * 系统日志，切面处理类
 * @author Aijm
 * @since 2019/10/9
 */
@Aspect
@AllArgsConstructor
@Slf4j
public class AspectAdminLog {

	private final SystemService systemService;

	@SneakyThrows
	@Around("@annotation(adminLog)")
	public Object saveSysLog(ProceedingJoinPoint point, AdminLog adminLog) {

		SysLog sysLog = new SysLog();
		if (adminLog != null) {
			// 注解上的描述
			sysLog.setOperation(adminLog.value());
		}
		// 请求的参数
		Object[] args = point.getArgs();
		String params = JSONUtil.toJsonStr(args[0]);
		sysLog.setContent(params);

		sysLog.setUserId(systemService.getUserId());
		Long startTime = System.currentTimeMillis();
		Object obj = point.proceed();
		Long endTime = System.currentTimeMillis();
		sysLog.setTime(endTime - startTime);
		sysLog.setResult(JSONUtil.toJsonStr(obj));
		log.info("切面日志:{}", JSONUtil.toJsonStr(sysLog));
		return obj;
	}

}
