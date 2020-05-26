package com.cloud.common.cache.constants;


/**
 * 枚举
 * @author Aijm
 * @since 2019/8/29
 */

public enum CacheScope {


     /**
      * 默认存储 系统级别
      */
     APPLICATION("application"),
     /**
      * 存储 部门信息
      */
     OFFICE("office"),



     //---------------------------------     关于auth系统级别缓存key     --------------------------------//
     /**
      * 授权
      */
     AUTH("auth"),

     /**
      * 客户端 client
      */
     AUTH_CLIENT("auth:client"),

     //---------------------------------     关于用户系统级别缓存key     --------------------------------//
     /**
      * 表示为用户级别  主要为了清空 user:*:{userId}
      */
     USER("user"),
     /**
      * 用户 存储了用户信息 （不包含office 和 角色）  user:user:{userId}
      */
     USER_USER("user:user"),


     /**
      *  存储用户和菜单之间的关系 user:menu:{userId}
      */
     USER_MENU("user:menu"),
     /**
      *  存储用户和菜单之间的关系 user:role:{userId}
      */
     USER_ROLE("user:role"),



     // ---------------------------------     关于用户系统级别缓存key 结束     --------------------------------//


     // ---------------------------------     关于字典系统级别缓存key 开始     --------------------------------//

     /**
      * 存储字典信息 主要为了清空dict:*:{type_code}
      */
     DICT("dict"),


     /**
      * 存储字典list信息 dict:list:{type_code}
      */
     DICT_LIST("dict:list"),


     /**
      * 存储字典tree信息 dict:tree:{type_code}
      */
     DICT_TREE("dict:tree"),
     // ---------------------------------     关于字典系统级别缓存key 结束     --------------------------------//

     // ---------------------------------  租户缓存key ---------------------------
     TENTANT_KEY("tentant");


     /**
      *  获取key
      */
     private String cacheName;

     CacheScope(String cacheName) {
          this.cacheName = cacheName;
     }

     public String getCacheName() {
          return cacheName;
     }

}
