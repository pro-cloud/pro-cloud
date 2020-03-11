package com.cloud.admin.api;


import com.cloud.admin.api.fallback.UserServiceFallback;
import com.cloud.admin.interfaces.UserService;
import com.cloud.common.util.client.CloudServiceList;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 用户信息
 * @author Aijm
 * @since 2019/5/11
 */
@FeignClient(contextId = "userService", value = CloudServiceList.CLOUD_ADMIN , fallback = UserServiceFallback.class)
public interface IUserService extends UserService {


}
