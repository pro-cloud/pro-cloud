package com.cloud.common.oauth.handler;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.map.MapUtil;

import cn.hutool.core.util.StrUtil;
import com.cloud.common.oauth.properties.SecurityProps;
import com.cloud.common.oauth.security.SecurityUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  APP环境下认证成功处理器
 * @author Aijm
 * @since 2019/5/27
 */
@Slf4j
@Component("proAuthenticationSuccessHandler")
public class ProAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ClientDetailsService clientDetailsService;

	@Autowired
	private SecurityProps securityProps;

	private RequestCache requestCache = new HttpSessionRequestCache();

	/**
	 * 用lazy 解决死循环问题
	 */
	@Lazy
	@Autowired
	private AuthorizationServerTokenServices authorizationServerTokenServices;

	private static final String BEARER_TOKEN_TYPE = "Basic ";

	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
		SecurityUser user = (SecurityUser)authentication.getPrincipal();
		SavedRequest savedRequest = this.requestCache.getRequest(request, response);
		String targetUrlParameter = this.getTargetUrlParameter();
		boolean status = !this.isAlwaysUseDefaultTargetUrl() && (targetUrlParameter == null || !StringUtils.hasText(request.getParameter(targetUrlParameter)));
		if (savedRequest != null && status) {
			this.clearAuthenticationAttributes(request);
			String targetUrl = savedRequest.getRedirectUrl();
			log.info("Redirecting to DefaultSavedRequest Url: {}", targetUrl);
			this.getRedirectStrategy().sendRedirect(request, response, targetUrl);
		} else {
			String header = request.getHeader(HttpHeaders.AUTHORIZATION);
			String clientId = securityProps.getClient().getClientId();
			String clientSecret = securityProps.getClient().getClientSecret();
			if (StrUtil.isNotBlank(header) && header.startsWith(BEARER_TOKEN_TYPE)) {
				String[] tokens = extractAndDecodeHeader(header);
				assert tokens.length == 2;
				clientId = tokens[0];
				clientSecret = tokens[1];
				log.info("请求头中信息为clientId:{};clientSecret:{}", clientId, clientSecret);
			}
			ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

			if (StrUtil.isBlank(clientSecret)) {
				throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
			} else {
				if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
					throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
				}
			}

			TokenRequest tokenRequest = new TokenRequest(MapUtil.newHashMap(), clientId, clientDetails.getScope(), "custom");
			OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
			OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
			OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
			response.setContentType("application/json;charset=UTF-8");

			log.info("用户【 {} 】记录登录日志", user.getUsername());
			response.getWriter().write(objectMapper.writeValueAsString(token));
		}


	}

	private String[] extractAndDecodeHeader(String header) {

		String base64Token = header.substring(6);
		String token = Base64.decodeStr(base64Token);
		log.info("解密后的token为:{}", token);
		int delim = token.indexOf(':');
		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}


}
