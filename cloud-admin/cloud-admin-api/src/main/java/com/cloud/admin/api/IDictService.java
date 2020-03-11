package com.cloud.admin.api;

import com.cloud.admin.interfaces.DictService;
import com.cloud.common.util.client.CloudServiceList;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author Aijm
 * @Description  字典暴露接口
 * @Date 2019/9/10
 */
@FeignClient(contextId = "dictService", value = CloudServiceList.CLOUD_ADMIN )
public interface IDictService extends DictService {

}
