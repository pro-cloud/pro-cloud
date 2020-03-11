package com.cloud.common.oauth.validate.image;



import com.cloud.common.oauth.validate.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


/**
 * 图片验证码
 * @author Aijm
 * @since 2019/5/26
 */
public class ImageCode extends ValidateCode {


	private static final long serialVersionUID = 5380211562245067696L;
	private transient String image;

	public ImageCode(String image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}

	public ImageCode(String image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
