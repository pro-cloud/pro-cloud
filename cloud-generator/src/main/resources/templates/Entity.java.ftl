package ${package}.${moduleName}.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#list columns as column>
    <#if 0 == column.columnName?index_of("is_")>
import com.baomidou.mybatisplus.annotation.TableField;
        <#break>
     </#if>
</#list>
import java.time.LocalDateTime;

/**
 * ${comments}
 *
 * @author ${author}
 * @date ${datetime}
 */
@Data
@TableName("${tableName}")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "${comments}")
public class ${className} extends BaseEntity<${className}> {
    private static final long serialVersionUID = 1L;

<#list columns as column>
   <#if column.lowerAttrName != "id" >
        <#if column.lowerAttrName != "createBy" >
            <#if column.lowerAttrName != "createDate" >
                <#if column.lowerAttrName != "updateBy" >
                    <#if column.lowerAttrName != "updateDate" >
                        <#if column.lowerAttrName != "remarks" >
                            <#if column.lowerAttrName != "delFlag" >
                                <#if 0 != column.columnName?index_of("is_")>
    @ApiModelProperty(value = "${column.comments}")
    private ${column.attrType} ${column.lowerAttrName};

                              <#else>
    @ApiModelProperty(value = "${column.comments}")
    @TableField("${column.columnName}")
    private ${column.attrType} has${column.lowerAttrName?substring(2)};

                               </#if>
                            </#if>
                        </#if>
                    </#if>
                </#if>
            </#if>
        </#if>
   </#if>
</#list>

}
