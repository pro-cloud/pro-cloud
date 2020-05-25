package com.cloud.common.data.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author Aijm
 * @Description 返回的code统一管理
 * @Date 2019/5/8
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    // 成功
    SUCCESS(200, "成功"),
    // token异常  3开头
    TOKEN_PAST(301, "token过期"),
    TOKEN_ERROR(302, "token异常"),
    // 登录异常
    LOGIN_ERROR(303, "登录异常"),
    LOGIN_LOCK(304, "用户被禁用"),

    LOGIN_NAME(305, "用户名错误"),
    LOGIN_NAME_NULL(306, "用户名为空"),
    LOGIN_PASSWORD(307, "密码错误"),
    LOGIN_CODE(308, "验证码错误"),
    LOGOUT_CODE(309, "退出失败，token 为空"),
    LOGIN_TENTANT(400, "租户不存在或者租户停用中"),


    // crud异常，4开头
    CRUD_SAVE_FAIL(403, "添加失败"),
    CRUD_UPDATE_FAIL(404, "更新失败"),
    CRUD_DELETE_FAIL(405, "删除失败"),
    CRUD_DELETE_NOT(406, "不允许删除"),
    CRUD_VALID_NOT(407, "字段校验异常"),
    CRUD_NOT_OPERATE(408, "无操作权限"),
    CRUD_LOCK_OPERATE(409, "没有获取到锁"),

    // 默认错误
    ERROR(999, "错误"),


    // 1000-2000 为sentinel异常 ///////////////////////////////

    SENTINEL_DEGRADE_ERROR(1000, "被降级规则阻挡"),
    SENTINEL_PARAM_ERROR(1001, "被热点参数规则阻挡"),
    SENTINEL_SYSTEM_ERROR(1002, "被系统规则阻挡"),
    SENTINEL_AUTHORITY_ERROR(1003, "被授权规则阻挡"),
    SENTINEL_ERROR(1999, "Unknown"),


    ////////////////2000-3000 为参数校验异常 ///////////////////////////

    //地址类异常
    PARAM_ADDR(2000, "参数邮件抄送地址为空,无法发送邮件!"),
    PARAM_ADDR_ATTACH(2001, "参数附件地址为空,无法发送邮件!"),
    PARAM_ADDR_ASC(2002, "参数邮件静态资源路径和文件名为空,无法发送邮件!"),


    ////////////////////// 3000-4000系统级别错误 ///////////////////////////

    SYSTEM_REQUEST_METHOD_NOT_SUPPORTED(3000, "请求方法不支持访问方式");
    private Integer code;

    private String desc;
}
