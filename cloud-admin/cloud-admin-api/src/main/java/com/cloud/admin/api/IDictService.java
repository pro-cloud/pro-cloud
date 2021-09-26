package com.cloud.admin.api;

import com.cloud.common.data.base.Result;
import com.cloud.common.util.client.CloudServiceList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Aijm
 * @Description  字典暴露接口 可以考虑去掉 建议使用dubber
 * @Date 2019/9/10
 */
@FeignClient(contextId = "dictService", value = CloudServiceList.CLOUD_ADMIN )
public interface IDictService {

    /**
     *  暴露接口使用
     * @param typeCode
     * @param type 0：list 集合 1：树
     * @return
     */
    @RequestMapping(value="/dict/type/{typeCode}/{type}", method= RequestMethod.GET)
    public Result getByType(@PathVariable("typeCode") String typeCode, @PathVariable("type") String type);
}
