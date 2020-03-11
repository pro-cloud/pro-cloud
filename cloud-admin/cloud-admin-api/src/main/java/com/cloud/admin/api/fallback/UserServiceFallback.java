package com.cloud.admin.api.fallback;

import com.cloud.admin.api.IUserService;
import com.cloud.admin.beans.po.SysUser;
import com.cloud.common.util.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserServiceFallback implements IUserService {

    @Override
    public Result<SysUser> infoByName(String username) {
        log.info("接口异常:{}", username);
        return null;
    }

    @Override
    public Result<SysUser> infoByMobile(String mobile) {
        log.info("接口异常:{}", mobile);
        return null;
    }
}
