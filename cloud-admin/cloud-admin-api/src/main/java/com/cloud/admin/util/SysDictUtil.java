package com.cloud.admin.util;

import com.cloud.admin.beans.po.SysDictList;
import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;

/**
 * @Author Aijm
 * @Description 字典工具类
 * @Date 2019/9/10
 */
@UtilityClass
public class SysDictUtil {


    /**
     * 字典list 转化为map
     * @param list
     * @return
     */
    public Map<String, String> listToMap(List<SysDictList> list) {

        Map<String, String> map = Maps.newHashMap();
        list.forEach(item -> {
            map.put(item.getLabel(), item.getValue());
        });
        return map;
    }
}
