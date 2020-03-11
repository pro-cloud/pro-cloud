package com.cloud.common.oauth.service;



import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 默认的 UserDetailsService 实现
 * 不做任何处理，只在控制台打印一句日志，然后抛出异常，提醒业务系统自己配置 UserDetailsService。
 * @author Aijm
 * @since 2019/5/27
 */
@Slf4j
public class DefaultUserDetailsServiceImpl implements ProUserDetailsService {


	@Override
	public UserDetails loadUserByUsername(String username) {
		return null;
	}

	@Override
	public UserDetails loadUserByMoblie(String mobile) {

		return null;
	}

	/**
	 * QQ 登录
	 *
	 * @param qqOpenid
	 * @return
	 */
	@Override
	public UserDetails loginByQQ(String qqOpenid) {
		return null;
	}

	/**
	 * 微信登录
	 *
	 * @param wxOpenid
	 * @return
	 */
	@Override
	public UserDetails loginByWX(String wxOpenid) {
		return null;
	}
}
