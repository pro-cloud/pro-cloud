package com.cloud.common.util.oauth;

import lombok.experimental.UtilityClass;

/**
 * 扩充的token
 * @author Aijm
 * @since 2019/5/15
 */
@UtilityClass
public class AdditionalToken {


    /**
     * 制作者
     */
    public static final String MAKE_BY = "makeBy";

    public static final String MAKER = "pro-cloud";

    public static final String USER_ID = "userId";

    /**
     * 用户类型
     */
    public static final String USER_TYPE = "userType";


    /**
     * 登录的用户名
     */
    public static final String LONGIN_NAME ="user_name";

    /**
     * 当前用户的姓名
     */
    public static final String USER_NAME ="name";

    /**
     * 管理的租户 ids
     */
    public static final String TENANT_IDS ="tenantIds";

}
