<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${package}.${moduleName}.mapper.${className}Mapper">


  <sql id="Base_Column_List">
    <#list columns as column>
      a.${column.columnName} AS <#if 0 != column.columnName?index_of("is_")>${column.lowerAttrName}<#else>has${column.lowerAttrName?substring(2)}</#if><#if column_has_next>,</#if>
    </#list>
  </sql>
</mapper>
