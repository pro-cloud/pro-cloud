package com.cloud.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.generator.entity.TableColumn;

import java.util.List;

/**
 * 字段
 * @author Aijm
 * @since 2020/5/14
 */
public interface TableColumnService  extends IService<TableColumn> {

    /**
     * 获取数据表字段
     * @param tableName
     * @return
     */
    List<TableColumn> getTableColumnList(String tableName);

    /**
     * 查询表备注信息
     * @param tableName
     * @return
     */
    String queryTableInfo(String tableName);
}
