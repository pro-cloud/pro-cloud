package com.cloud.common.oauth.authentication.mobile;


import com.cloud.common.oauth.service.ProUserDetailsService;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * @Author Aijm
 * @Description 封装手机模块  不能用@service
 * @Date 2019/5/20
 */
@Data
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private ProUserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
		UserDetails user = userDetailsService.loadUserByMoblie((String) authenticationToken.getPrincipal());
		if (user == null) {
			throw new UsernameNotFoundException("根据手机号无法查询到用户");
		}
		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
		authenticationResult.setDetails(authenticationToken.getDetails());
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}


}
