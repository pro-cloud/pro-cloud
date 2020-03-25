package com.cloud.admin.beans.dto;

import com.cloud.admin.beans.po.SysDict;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author Aijm
 * @Description 字典类型表
 * @Date 2019/9/5
 */
@Data
@Accessors(chain = true)
public class DictDTO extends SysDict {

    /**
     * 字典结构类型  0：list 集合 1：树
     */
    public static final String DICT_TREE = "1";

    public static final String DICT_LIST = "0";

    /**
     * 字典类型  0：普通字典 1：系统级别数据 不允许删除
     */
    public static final Integer DICT_SYS = 1;

    public static final Integer DICT_NO_SYS = 0;
}
