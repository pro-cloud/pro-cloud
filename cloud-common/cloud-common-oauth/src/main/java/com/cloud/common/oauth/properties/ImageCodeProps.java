package com.cloud.common.oauth.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author Aijm
 * @Description 图片验证码配置项
 * @Date 2019/5/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCodeProps extends SmsCodeProps {


	ImageCodeProps() {
		super.setLength(4);
	}

	/**
	 * 图片宽
	 */
	private int width = 100;
	/**
	 * 图片高
	 */
	private int height = 33;

}
