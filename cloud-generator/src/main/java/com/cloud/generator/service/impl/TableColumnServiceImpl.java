package com.cloud.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.generator.entity.TableColumn;
import com.cloud.generator.mapper.TableColumnMapper;
import com.cloud.generator.service.TableColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字段
 * @author Aijm
 * @since 2020/5/14
 */
@Service
public class TableColumnServiceImpl extends ServiceImpl<TableColumnMapper, TableColumn> implements TableColumnService {

    @Autowired
    private TableColumnMapper tableColumnMapper;

    /**
     * 获取数据表字段
     *
     * @param tableName
     * @return
     */
    @Override
    public List<TableColumn> getTableColumnList(String tableName) {
        return tableColumnMapper.getTableColumnList(tableName);
    }

    /**
     * 查询表备注信息
     *
     * @param tableName
     * @return
     */
    @Override
    public String queryTableInfo(String tableName) {
        return tableColumnMapper.queryTableInfo(tableName);
    }
}
