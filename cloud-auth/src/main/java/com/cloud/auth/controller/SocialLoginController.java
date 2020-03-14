package com.cloud.auth.controller;

import cn.hutool.core.map.MapUtil;
import com.cloud.auth.config.CustomTokenEnhancer;
import com.cloud.auth.factory.SocialLoginFactory;
import com.cloud.common.controller.base.BaseController;
import com.cloud.common.oauth.authentication.social.SocialCodeAuthenticationToken;
import com.cloud.common.oauth.properties.SecurityProps;
import com.cloud.common.oauth.security.SecurityUser;
import com.cloud.common.util.base.Result;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Aijm
 * @Description 登录
 * @Date 2019/12/9
 */
@RestController
@RequestMapping("/social")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SocialLoginController extends BaseController {


    private final AuthRequestFactory factory;

    private final SecurityProps securityProps;
    private final ClientDetailsService clientDetailsService;
    private final TokenStore tokenStore;
    private final CustomTokenEnhancer customTokenEnhancer;
    private final SocialLoginFactory socialLoginFactory;

    /**
     * 登录类型
     */
    @GetMapping
    public Map<String, String> loginType() {
        List<String> oauthList = factory.oauthList();
        return oauthList.stream().collect(Collectors.toMap(oauth -> oauth.toLowerCase() + "登录", oauth -> "http://xxxx/xx/oauth/login/" + oauth.toLowerCase()));
    }

    /**
     * 登录
     *
     * @param oauthType 第三方登录类型
     * @param response  response
     * @throws IOException
     */
    @PostMapping("/login/{oauthType}")
    public void renderAuth(@PathVariable String oauthType, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(oauthType);
        response.sendRedirect(authRequest.authorize(oauthType + "::" + AuthStateUtils.createState()));
    }

    /**
     * 登录成功后的回调
     *
     * @param oauthType 第三方登录类型
     * @param callback  携带返回的信息
     * @return 登录成功后的信息
     */
    @PostMapping("/{oauthType}/callback")
    public Result login(@PathVariable String oauthType, AuthCallback callback) {
        AuthRequest authRequest = factory.get(oauthType);
        AuthResponse response = authRequest.login(callback);

        SecurityUser user;
        try {
            user = socialLoginFactory.getSecurityUser(oauthType,"");
        } catch (Exception ex){
            // todo 将 获取到的用户信息返回并插入到数据库中
            user = null;
        }

        OAuth2AccessToken token = getoAuth2AccessToken(user);
        return Result.success(token);
    }

    /**
     * 将用户信息转化为token
     * @param user
     * @return
     */
    private OAuth2AccessToken getoAuth2AccessToken(SecurityUser user) {
        SocialCodeAuthenticationToken authentication = new SocialCodeAuthenticationToken(user, AuthorityUtils.NO_AUTHORITIES);
        String clientId = securityProps.getClient().getClientId();
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        TokenRequest tokenRequest = new TokenRequest(MapUtil.newHashMap(), clientId, clientDetails.getScope(), "app");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        defaultTokenServices.setTokenEnhancer(customTokenEnhancer);
        return defaultTokenServices.createAccessToken(oAuth2Authentication);
    }


}
