package com.cloud.auth.factory;


import cn.hutool.core.util.EnumUtil;
import com.cloud.common.oauth.security.SecurityUser;
import com.cloud.common.oauth.service.ProUserDetailsService;
import me.zhyd.oauth.config.AuthDefaultSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Aijm
 * @Description 第三方登录从数据库中获取到用户的信息
 * @Date 2019/12/9
 */
@Service
public class SocialLoginFactory {

    @Autowired
    private ProUserDetailsService proUserDetailsService;


    /**
     * 获取数据库中的SecurityUser 信息
     * @param oauthType
     * @param openid
     * @return
     */
    public SecurityUser getSecurityUser(String oauthType, String openid){
        AuthDefaultSource authDefaultSource;
        try {
            authDefaultSource = EnumUtil.fromString(AuthDefaultSource.class, oauthType.toUpperCase());
        } catch (IllegalArgumentException var4) {
            return null;
        }
        switch(authDefaultSource) {
            case QQ:
                return getQQSecurityUser(openid);
            case WECHAT:
                return getWXSecurityUser(openid);
            default:
                return null;
        }
    }

    /**
     * 为qq时的查询
     * @param openid
     * @return
     */
    private SecurityUser getQQSecurityUser(String openid){
        return (SecurityUser) proUserDetailsService.loginByQQ(openid);
    }

    /**
     * 为微信时的查询
     * @param openid
     * @return
     */
    private SecurityUser getWXSecurityUser(String openid){
        return (SecurityUser) proUserDetailsService.loginByWX(openid);
    }

}
