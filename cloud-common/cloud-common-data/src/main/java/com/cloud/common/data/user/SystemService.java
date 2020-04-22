package com.cloud.common.data.user;


/**
 * 用户信息接口 主要提供给ObjUtil
 * @author Aijm
 * @since 2019/8/25
 */
public interface SystemService {

    /**
     * 获取到登录用户的id 为了封装
     * @return
     */
    Long getUserId();


    /**
     * 获取到用户的租户id集合
     * @return
     */
    String getUserTenantIds();

}
