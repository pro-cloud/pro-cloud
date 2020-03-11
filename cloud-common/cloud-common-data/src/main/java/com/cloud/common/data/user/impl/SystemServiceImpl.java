package com.cloud.common.data.user.impl;

import com.cloud.common.data.user.SystemService;

/**
 * 默认用户信息
 * @author Aijm
 * @since 2019/8/25
 */
public class SystemServiceImpl implements SystemService {

    @Override
    public Long getUserId() {
        return 1L;
    }


}
