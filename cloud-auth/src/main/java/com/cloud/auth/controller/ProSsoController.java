package com.cloud.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.cloud.auth.util.SecurityUtil;
import com.cloud.common.cache.constants.CacheScope;
import com.cloud.common.cache.util.CacheUtil;
import com.cloud.common.oauth.security.SecurityUser;
import com.cloud.common.data.base.Result;
import com.cloud.common.data.enums.ResultEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Aijm
 * @date 2019/09/06
 * 删除token端点
 */
@RestController
@AllArgsConstructor
@RequestMapping("/token")
public class ProSsoController {

	private final ClientDetailsService clientDetailsService;
	private final TokenStore tokenStore;

    /**
	 * 认证页面
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/ssoLogin")
	public ModelAndView singleSignOn(ModelAndView modelAndView) {
		modelAndView.setViewName("ftl/ssoLogin");
		return modelAndView;
	}

	/**
	 * 确认授权页面
	 *
	 * @param request
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/confirm")
	public ModelAndView confirm(HttpServletRequest request, ModelAndView modelAndView) {
		// todo
		modelAndView.setViewName("ftl/confirm");
		return modelAndView;
	}


    /**
     *  退出登录
	 * @param header
     * @return
     */
	@DeleteMapping("/logout")
	public Result logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String header) {
		if (StrUtil.isBlank(header)) {
			return Result.error(ResultEnum.LOGOUT_CODE);
		}
		String tokenValue = header.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
		if (accessToken == null || StrUtil.isBlank(accessToken.getValue())) {
			return Result.error(ResultEnum.TOKEN_ERROR);
		}

		OAuth2Authentication auth = tokenStore.readAuthentication(accessToken);
		SecurityUser user = (SecurityUser)auth.getPrincipal();
		// 清空菜单信息
		CacheUtil.remove(CacheScope.USER_MENU.getCacheName(), user.getUserId().toString());
		// 清空角色信息
		CacheUtil.remove(CacheScope.USER_ROLE.getCacheName(), user.getUserId().toString());
		// 清空用户信息
		CacheUtil.remove(CacheScope.USER_USER.getCacheName(), user.getUserId().toString());
		// 清空access token
		tokenStore.removeAccessToken(accessToken);
		// 清空 refresh token
		OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
		tokenStore.removeRefreshToken(refreshToken);
		return Result.success("退出登录成功!");
	}

	/**
	 * 令牌管理调用
	 *
	 * @param token token
	 * @return
	 */
	@DeleteMapping("/{token}")
	public Result delToken(@PathVariable("token") String token) {
		OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
		tokenStore.removeAccessToken(oAuth2AccessToken);
		return Result.success("删除成功!");
	}



}
