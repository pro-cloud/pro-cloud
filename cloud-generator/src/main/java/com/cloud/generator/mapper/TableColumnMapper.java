package com.cloud.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.generator.entity.TableColumn;

import java.util.List;

/**
 *  查询表信息
 * @author Aijm
 * @since 2020/5/14
 */
public interface TableColumnMapper extends BaseMapper<TableColumn> {


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
