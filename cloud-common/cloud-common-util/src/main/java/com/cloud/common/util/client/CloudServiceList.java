package com.cloud.common.util.client;


import lombok.experimental.UtilityClass;

/**
 * 所有的feign
 * @author Aijm
 * @since 2019/5/4
 */
@UtilityClass
public class CloudServiceList {

    /**
     * 网关
     */
    public static final String CLOUD_GATEWAY = "cloud_gateway";

    /**
     * 用户模块
     */
    public static final String CLOUD_ADMIN = "cloud-admin-service";


    /**
     * 短信模块
     */
    public static final String CLOUD_MESSAGE = "cloud-message-service";


    /**
     * 认证模块
     */
    public static final String CLOUD_AUTH = "cloud-auth";
}
