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
    REMOTE_ERROR(304, "异地登录"),

    LOGIN_NAME(305, "用户名错误"),
    LOGIN_NAME_NULL(306, "用户名为空"),
    LOGIN_PASSWORD(307, "密码错误"),
    LOGIN_CODE(308, "验证码错误"),
    LOGOUT_CODE(309, "退出失败，token 为空"),


    // crud异常，4开头
    CRUD_SAVE_FAIL(403, "添加失败"),
    CRUD_UPDATE_FAIL(404, "更新失败"),
    CRUD_DELETE_FAIL(405, "删除失败"),
    CRUD_DELETE_NOT(406, "不允许删除"),
    CRUD_VALID_NOT(407, "字段校验异常"),
    CRUD_NOT_OPERATE(408, "无操作权限"),
    CRUD_LOCK_OPERATE(409, "没有获取到锁"),

    COLLECTION(410, "已收藏"),
    USER_ADVICE(411, "保存建议失败,不能重复提建议"),

    // 用户异常，5开头
    LECTURER_REQUISITION_REGISTERED(501, "申请失败！该手机没注册，请先注册账号"),
    LECTURER_REQUISITION_WAIT(502, "申请失败！该账号已提交申请，待审核中，在7个工作日内会有相关人员与您联系确认"),
    LECTURER_REQUISITION_YET(503, "申请失败！该账号已提交申请，请直接登录"),
    //
    USER_SAVE_FAIL(504, "添加失败"),
    USER_UPDATE_FAIL(505, "更新失败"),

    // 错误
    ERROR(999, "错误"),


    // 1000-2000 为sentinel异常

    SENTINEL_DEGRADE_ERROR(1000, "被降级规则阻挡"),
    SENTINEL_PARAM_ERROR(1001, "被热点参数规则阻挡"),
    SENTINEL_SYSTEM_ERROR(1002, "被系统规则阻挡"),
    SENTINEL_AUTHORITY_ERROR(1003, "被授权规则阻挡"),
    SENTINEL_ERROR(1999, "Unknown"),


    //2000-3000 为参数校验异常

    //地址类异常
    PARAM_ADDR(2000, "参数邮件抄送地址为空,无法发送邮件!"),
    PARAM_ADDR_ATTACH(2001, "参数附件地址为空,无法发送邮件!"),
    PARAM_ADDR_ASC(2002, "参数邮件静态资源路径和文件名为空,无法发送邮件!");



    private Integer code;

    private String desc;
}
