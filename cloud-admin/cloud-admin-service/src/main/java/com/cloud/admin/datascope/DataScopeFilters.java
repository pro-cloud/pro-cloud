package com.cloud.admin.datascope;

import cn.hutool.core.util.StrUtil;
import com.cloud.admin.beans.po.SysOffice;
import com.cloud.admin.util.OfficeUtil;
import com.cloud.admin.util.UserUtil;
import com.cloud.common.entity.BaseEntity;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;


/**
 * @Author Aijm
 * @Description 对数据权限的实现
 * @Date 2019/5/8
 */
@Slf4j
@UtilityClass
public class DataScopeFilters {


    /**
     * 部门关联字段 id
     */
    private static final String SQL_ID = ".id = '";


    /**
     * 数据范围过滤 默认关联create_by
     * @param entity 需要数据权限的实体
     * @param officeAlias 机构表别名
     * @param masterAlias 主表别名
     * @param <T> 含有数据权限的实体
     */
    public <T extends BaseEntity> void dataScopeEntity(T entity, String officeAlias, String masterAlias){
        String dsf = dataScopeFilter(officeAlias, masterAlias);
        entity.getSqlMap().put("dsf", dsf);
    }

    /**
     * 数据范围过滤 默认关联create_by 不建议使用
     *     建议使用 dataScopeEntity
     * @param officeAlias 机构表别名
     * @param masterAlias 主表别名
     * @return 标准连接条件对象
     */
    public static String dataScopeFilter(String officeAlias, String masterAlias) {
        // 获取到登录的用户信息

        StringBuilder sqlString = new StringBuilder();

        // 获取到最大的数据权限范围
        int dataScopeInteger = UserUtil.getMaxDataScope();
        // 超级管理员，跳过权限过滤
        Long userId = UserUtil.getUserId();
        SysOffice userOffice = OfficeUtil.getUserOffice();
        if (!UserUtil.hasAdmin(userId)){
            boolean isDataScopeAll = false;

            if (DataScope.DATA_SCOPE_ALL == dataScopeInteger){
                isDataScopeAll = true;
            } else if (DataScope.DATA_SCOPE_OFFICE_AND_CHILD == dataScopeInteger ){
                sqlString.append(officeAlias + SQL_ID + userOffice.getId() + "'");
                sqlString.append(" OR " + officeAlias + ".parent_ids LIKE '" + userOffice.getParentIds() + userOffice.getId() + ",%'");
            } else if (DataScope.DATA_SCOPE_OFFICE == dataScopeInteger){
                sqlString.append(officeAlias + SQL_ID + userOffice.getId() + "'");
            } else if (DataScope.DATA_SCOPE_SELF == dataScopeInteger) {
                sqlString.append(masterAlias + ".create_by = '" + userId + "'");
            }

            // 如果没有全部数据权限，并设置了用户别名，则当前权限为本人；如果未设置别名，当前无权限为已植入权限
            if (!isDataScopeAll && StrUtil.isNotBlank(sqlString.toString())){
                return " AND (" + sqlString.toString() + ")";
            }

        }
        return "";
    }

    /**
     * 数据范围过滤
     * @param entity  需要数据权限的实体
     * @param officeAlias 机构表别名
     * @param masterAlias 用户id字段表别名
     * @param field 用户id字段
     * @param <T> 含有数据权限的实体
     */
    public <T extends BaseEntity> void dataScopeEntity(T entity, String officeAlias,
                         String masterAlias, String field){
        String dsf = dataScopeFilter(officeAlias, masterAlias, field);
        entity.getSqlMap().put("dsf", dsf);
    }

    /**
     * 数据范围过滤 不建议使用
     *      建议使用 dataScopeEntity
     * @param officeAlias 机构表别名
     * @param alias 用户id字段表别名
     * @param field 用户id字段
     * @return 标准连接条件对象
     */
    private static String dataScopeFilter(String officeAlias, String alias, String field) {
        // 获取到登录的用户信息

        StringBuilder sqlString = new StringBuilder();

        // 获取到最大的数据权限范围
        int dataScopeInteger = UserUtil.getMaxDataScope();
        // 超级管理员，跳过权限过滤
        Long userId = UserUtil.getUserId();
        SysOffice userOffice = OfficeUtil.getUserOffice();
        if (!UserUtil.hasAdmin(userId)){
            boolean isDataScopeAll = false;

            if (DataScope.DATA_SCOPE_ALL == dataScopeInteger){
                isDataScopeAll = true;
            } else if (DataScope.DATA_SCOPE_OFFICE_AND_CHILD == dataScopeInteger){
                sqlString.append(officeAlias + SQL_ID + userOffice.getId() + "'");
                sqlString.append(" OR " + officeAlias + ".parent_ids LIKE '" + userOffice.getParentIds() + userOffice.getId() + ",%'");
            } else if (DataScope.DATA_SCOPE_OFFICE == dataScopeInteger){
                sqlString.append(officeAlias + SQL_ID + userOffice.getId() + "'");
            } else if (DataScope.DATA_SCOPE_SELF == dataScopeInteger) {
                sqlString.append(alias + "."+field+"= '" + userId + "'");
            }

            // 如果没有全部数据权限，并设置了用户别名，则当前权限为本人；如果未设置别名，当前无权限为已植入权限
            if (!isDataScopeAll && StrUtil.isNotBlank(sqlString.toString())){
                return " AND (" + sqlString.toString() + ")";
            }

        }
        return "";
    }


}
