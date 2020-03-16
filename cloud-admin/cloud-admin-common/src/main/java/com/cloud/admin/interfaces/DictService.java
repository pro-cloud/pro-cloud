package com.cloud.admin.interfaces;

import com.cloud.common.data.base.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Aijm
 * @Description  字典暴露接口
 * @Date 2019/9/10
 */
public interface DictService {


    /**
     *  暴露接口使用
     * @param typeCode
     * @param type 0：list 集合 1：树
     * @return
     */
    @RequestMapping(value="/dict/{typeCode}/{type}", method= RequestMethod.GET)
    public Result getByType(@PathVariable("typeCode") String typeCode, @PathVariable("type") String type);
}
