package com.cloud.admin.util;

import com.cloud.admin.beans.po.SysDictList;
import com.cloud.admin.service.SysDictListService;
import com.cloud.common.data.util.SpringUtil;
import com.cloud.common.util.util.StrUtils;
import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * @Author Aijm
 * @Description 字典工具类
 * @Date 2019/9/10
 */
@UtilityClass
public class DictUtil {


    private static SysDictListService sysDictListService = SpringUtil.getBean(SysDictListService.class);



    /**
     * 通过typecode查询字典项list ;
     * @param typeCode  code
     * @param label 字典数据的标签
     * @return
     */
    public static List<SysDictList> getDictLists(String typeCode, String label) {
        // 获取到 typeCode下所有的数据
        List<SysDictList> dicts = sysDictListService.getDictListByType(typeCode);
        if (StrUtils.isBlank(label)) {
            return dicts;
        }
        // 遍历获取到含有label的字典
        List<SysDictList> list = Lists.newArrayList();
        for (SysDictList dict : dicts) {
            if (dict.getLabel().contains(label)) {
                list.add(dict);
            }
        }
        return list;
    }


    /**
     * 获取到键值对值 name(key)-> value
     * @param typeCode 类型code
     * @param name key
     * @param defaultValue 默认值
     * @return
     */
    public static String getDictLabel(String typeCode, String name, String defaultValue){
        if (StrUtils.isNotBlank(typeCode) && StrUtils.isNotBlank(name)){
            for (SysDictList dict : getDictLists(typeCode, null)){
                if (name.equals(dict.getName())){
                    return dict.getValue();
                }
            }
        }
        return defaultValue;
    }


    /**
     * 获取到键值对值 name(key)-> value
     * @param typeCode 类型code
     * @param name key
     * @return
     */
    public static SysDictList getDictList(String typeCode, String name){
        if (StrUtils.isNotBlank(typeCode) && StrUtils.isNotBlank(name)){
            for (SysDictList dict : getDictLists(typeCode, null)){
                if (name.equals(dict.getName())){
                    return dict;
                }
            }
        }
        return null;
    }

}
