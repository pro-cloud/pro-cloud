package com.cloud.common.oauth.properties;


import lombok.experimental.UtilityClass;

/**
 * @Author Aijm
 * @Description SecurityConstants 静态类
 * @Date 2019/11/4
 */
@UtilityClass
public class SecurityConstants {


	/////////////////////////////手机登录配置//////////////////////////////////////
	/**
	 * 默认的手机验证码登录请求处理url
	 */
	public static final String DEFAULT_SIGN_IN_URL_MOBILE = "/auth/mobile";

	/**
	 * 手机登录时传递的参数 mobile:18210584253
	 */
	public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

	///////////////////////////验证码///////////////////////////////////////////////

	/**
	 * 验证短信验证码时 http请求中默认的携带短信验证码信息的参数的名称
	 */
	public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";


	/**
	 * 图片验证码 http请求中默认的携带图片验证码信息的参数的名称
	 */
	public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

	/**
	 * 验证邮箱验证码时，http请求中默认的携带短信验证码信息的参数的名称
	 */
	public static final String DEFAULT_PARAMETER_NAME_CODE_EMAIL = "emailCode";


    ////////////////////////////////需要校验 验证码的url地址////////////////////////////////////////

	/**
	 * 登录页面
	 */
	public static final String DEFAULT_UNAUTHENTICATION_URL = "/token/ssoLogin";
	/**
	 * 默认的用户名密码登录请求处理url  需要验证码
	 */
	public static final String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/auth/form";



	//////////////////////////// 需要获取code的url//////////////////////////////////

	public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/auth/code";





}
