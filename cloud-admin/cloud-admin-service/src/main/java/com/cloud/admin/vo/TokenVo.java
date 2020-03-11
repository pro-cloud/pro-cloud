package com.cloud.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author Aijm
 * @Description 返回token的map
 * @Date 2019/7/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 存储返回的token信息
     */
    private Map token;
}
